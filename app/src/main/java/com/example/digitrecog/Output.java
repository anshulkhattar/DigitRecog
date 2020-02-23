package com.example.digitrecog;

public class Output {

    private final int mNumber;

    public Output(float[] probs) {
        mNumber = argmax(probs);
    }

    public int getNumber() {
        return mNumber;
    }

    private static int argmax(float[] probs) {
        int maxIdx = -1;
        float maxProb = 0.0f;
        for (int i = 0; i < probs.length; i++) {
            if (probs[i] > maxProb) {
                maxProb = probs[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}