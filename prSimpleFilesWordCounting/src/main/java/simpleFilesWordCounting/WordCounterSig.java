package simpleFilesWordCounting;

import java.util.*;
import java.io.*;

public class WordCounterSig extends WordCounter {
    private static final int INITIAL_SIZE_NSW = 10;
    private int nonSigWordsNum;
    private String[] nonSignificant;

    public WordCounterSig() {
        super();
        nonSignificant = new String[INITIAL_SIZE_NSW];
        nonSigWordsNum = 0;
    }


    public WordCounterSig(int size, String[] inputs) {
        super(size);
        nonSignificant = inputs;
        nonSigWordsNum = inputs.length;
    }

    public WordCounterSig(String fileNoSig, String del) throws FileNotFoundException {
        super();
        nonSignificant = new String[INITIAL_SIZE_NSW];
        nonSigWordsNum = 0;
        readNonSigFile(fileNoSig, del);
    }

    public WordCounterSig(int size, String fileNoSig, String del) throws FileNotFoundException {
        super(size);
        nonSignificant = new String[INITIAL_SIZE_NSW];
        nonSigWordsNum = 0;
        readNonSigFile(fileNoSig, del);
    }

    public void readNonSigFile(String fileNoSig, String del) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileNoSig));
        readNonSigWords(sc, del);
    }

    private void readNonSigWords(Scanner sc, String del) {
        while (sc.hasNextLine()) {
            readNonSigWords(sc.nextLine(), del);
        }
        sc.close();
    }

    private void readNonSigWords(String line, String del) {
        Scanner scc = new Scanner(line);
        scc.useDelimiter(del);
        while (scc.hasNext()) {
            includeNoSig(scc.next());
        }
        scc.close();
    }

    protected void include(String word) {
        if (findNoSig(word) == -1) {
            super.include(word);
        }
    }

    private void includeNoSig(String word) {
        int index = findNoSig(word);
        if (index == -1) {
            if (nonSignificant.length <= nonSigWordsNum) {
                nonSignificant = Arrays.copyOf(nonSignificant, nonSigWordsNum + 1);
            }
            nonSignificant[nonSigWordsNum] = word;
            nonSigWordsNum++;
        }
    }

    private int findNoSig(String word) {
        int i = 0;
        if (nonSigWordsNum != 0) {
            while (i < nonSigWordsNum) {
                if (nonSignificant[i].compareToIgnoreCase(word) == 0) {
                    break;
                } else {
                    i++;
                }
            }
        }
        return (i == nonSigWordsNum || nonSigWordsNum == 0) ? -1 : i;
    }
    
    public void readNonSigArray(String[] nonSig) {
        for (String word : nonSig) {
            includeNoSig(word);
        }
    }

}

