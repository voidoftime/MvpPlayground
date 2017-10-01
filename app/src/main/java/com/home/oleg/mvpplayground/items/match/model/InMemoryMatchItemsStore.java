package com.home.oleg.mvpplayground.items.match.model;


import com.annimon.stream.IntPair;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.reactivex.subjects.PublishSubject;

public class InMemoryMatchItemsStore implements MatchItemsStore {
    private List<WordPair> wordPairs = Arrays.asList(
            new WordPair("world", "мир"),
            new WordPair("concern", "беспокойство"),
            new WordPair("sheep", "овца"),
            new WordPair("car", "машина"),
            new WordPair("bike", "велосипед")
    );
    private List<Integer> foreignWordsOrder;
    private List<Integer> nativeWordsOrder;
    private String selectedForeignWord;
    private String selectedNativeWord;
    private PublishSubject<List<WordPair>> onWordsListChange = PublishSubject.create();

    public InMemoryMatchItemsStore() {
        prepareSession();
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
        return Stream.of(foreignWordsOrder)
                .map(wordPairs::get)
                .map(WordPair::getForeignWord)
                .toList();
    }

    @Override
    public List<String> getNativeWords() {
        return Stream.of(nativeWordsOrder)
                .map(wordPairs::get)
                .map(WordPair::getNativeWord)
                .toList();
    }

    @Override
    public void prepareSession() {
        foreignWordsOrder = new ArrayList<>();
        nativeWordsOrder = new ArrayList<>();
        Stream.range(0, wordPairs.size())
                .forEach(index -> {
                    foreignWordsOrder.add(index);
                    nativeWordsOrder.add(index);
                });
        Collections.shuffle(foreignWordsOrder);
        Collections.shuffle(nativeWordsOrder);
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
