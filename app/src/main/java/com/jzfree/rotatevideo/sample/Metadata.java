package com.jzfree.rotatevideo.sample;

/**
 * Created by Wang Jiuzhou on 2018/7/9 16:57
 */
public class Metadata {
    private String mKey;
    private Object mValue;

    public Metadata(String key, Object value) {
        mKey = key;
        mValue = value;
    }

    /**
     * @return the mKey
     */
    public String getKey() {
        return mKey;
    }

    /**
     * @param mKey the mKey to set
     */
    public void setKey(String mKey) {
        this.mKey = mKey;
    }

    /**
     * @return the mValue
     */
    public Object getValue() {
        return mValue;
    }

    /**
     * @param mValue the mValue to set
     */
    public void setValue(Object mValue) {
        this.mValue = mValue;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "mKey='" + mKey + '\'' +
                ", mValue=" + mValue +
                '}';
    }
}
