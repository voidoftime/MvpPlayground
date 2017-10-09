package com.home.oleg.mvpplayground.items.match.model;


import com.annimon.stream.IntPair;
import com.annimon.stream.Stream;
import com.home.oleg.mvpplayground.api.ItemsMatchWordsProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class InMemoryMatchItemsStore implements MatchItemsStore {
    ItemsMatchWordsProvider wordsProvider;

    private List<WordPair> allWordPairs;
    private List<WordPair> wordPairs=Collections.emptyList();
//    = Arrays.asList(
//            new WordPair("world", "мир"),
//            new WordPair("concern", "беспокойство"),
//            new WordPair("sheep", "овца"),
//            new WordPair("car", "машина"),
//            new WordPair("bike", "велосипед")
//    );
    private List<Integer> foreignWordsOrder;
    private List<Integer> nativeWordsOrder;
    private String selectedForeignWord;
    private String selectedNativeWord;
    private PublishSubject<List<WordPair>> onWordsListChange = PublishSubject.create();
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread());
    private int wordsOffset = 0;
    private int maxWordsCountInSession = 3;
    private boolean sessionPrepared=false;

    @Inject
    public InMemoryMatchItemsStore(ItemsMatchWordsProvider wordsProvider) {
        this.wordsProvider = wordsProvider;
        wordsProvider.getWords()
                .subscribe(values -> {
                    allWordPairs = values;

                    prepareSession();
                });
    }


    @Override
    public String getForeignSelectedWord() {
        return selectedForeignWord;
    }

    @Override
    public String getNativeSelectedWord() {
        return selectedNativeWord;
    }

    @Override
    public void setForeignSelectedWord(String value) {
        selectedForeignWord = value;
    }

    @Override
    public void setNativeSelectedWord(String value) {
        selectedNativeWord = value;
    }

    @Override
    public List<String> getForeignWords() {
        if (!sessionPrepared) return Collections.emptyList();
        return Stream.of(foreignWordsOrder)
                .map(wordPairs::get)
                .map(WordPair::getForeignWord)
                .toList();
    }

    @Override
    public List<String> getNativeWords() {
        if (!sessionPrepared) return Collections.emptyList();
        return Stream.of(nativeWordsOrder)
                .map(wordPairs::get)
                .map(WordPair::getNativeWord)
                .toList();
    }

    @Override
    public void prepareSession() {
        foreignWordsOrder = new ArrayList<>();
        nativeWordsOrder = new ArrayList<>();
        int sessionWordsCount = Math.min(maxWordsCountInSession, allWordPairs.size() - wordsOffset);
        wordPairs = allWordPairs.subList(
                wordsOffset,
                wordsOffset + sessionWordsCount);
        Stream.range(0, wordPairs.size())
                .forEach(index -> {
                    foreignWordsOrder.add(index);
                    nativeWordsOrder.add(index);
                });
        Collections.shuffle(foreignWordsOrder);
        Collections.shuffle(nativeWordsOrder);
        wordsOffset+=sessionWordsCount;
        sessionPrepared=true;
        onWordsListChange.onNext(wordPairs);
    }

    @Override
    public boolean isWordsMatch() {
        if (selectedForeignWord == null || selectedNativeWord == null) return false;
        return Stream.of(wordPairs)
                .filter(wordPair -> wordPair.foreignWord.equals(selectedForeignWord))
                .findFirst()
                .map(wordPair -> wordPair.nativeWord.equals(selectedNativeWord))
                .orElse(false);

    }

    @Override
    public void removeSelectedWord() {
        Integer selectedForeignWordIndex = getSelectedForeignWordIndex();
        Integer selectedNativeWordIndex = getSelectedNativeWordIndex();
        foreignWordsOrder = Stream.of(foreignWordsOrder)
                .filter(index -> !Objects.equals(index, selectedForeignWordIndex))
                .map(index -> index > selectedForeignWordIndex ? index - 1 : index)
                .toList();
        nativeWordsOrder = Stream.of(nativeWordsOrder)
                .filter(index -> !Objects.equals(index, selectedNativeWordIndex))
                .map(index -> index > selectedNativeWordIndex ? index - 1 : index)
                .toList();

        wordPairs = Stream.of(wordPairs)
                .filter(wordPair -> !wordPair.foreignWord.equals(selectedForeignWord))
                .toList();

        selectedForeignWord = null;
        selectedNativeWord = null;
        if(wordPairs.isEmpty()){
            prepareSession();
        }
        onWordsListChange.onNext(wordPairs);
    }

    private Integer getSelectedForeignWordIndex() {
        return Stream.of(wordPairs).indexed()
                .filter(pair -> pair.getSecond().foreignWord.equals(selectedForeignWord))
                .findFirst()
                .map(IntPair::getFirst)
                .orElseThrow(() -> new RuntimeException("not found by selFor"));
    }

    private Integer getSelectedNativeWordIndex() {
        return Stream.of(wordPairs).indexed()
                .filter(pair -> pair.getSecond().nativeWord.equals(selectedNativeWord))
                .findFirst()
                .map(IntPair::getFirst)
                .orElseThrow(() -> new RuntimeException("not found by selNat"));
    }

    @Override
    public PublishSubject<List<WordPair>> getOnWordsListChange() {
        return onWordsListChange;
    }
}
