package com.chen.account.entity.stockmarket;

/**
 * author long
 * <p>
 * date 17-9-14
 * <p>
 * desc
 */

public class Result {

    private HKData data;
    private Gopicture gopicture;
    private HengshengData hengsheng_data;
    public void setData(HKData data) {
        this.data = data;
    }
    public HKData getData() {
        return data;
    }

    public void setGopicture(Gopicture gopicture) {
        this.gopicture = gopicture;
    }
    public Gopicture getGopicture() {
        return gopicture;
    }

    public void setHengsheng_data(HengshengData hengsheng_data) {
        this.hengsheng_data = hengsheng_data;
    }
    public HengshengData getHengsheng_data() {
        return hengsheng_data;
    }

}
