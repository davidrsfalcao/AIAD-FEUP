package utils;

import agents.PostOffice;

import java.util.concurrent.TimeUnit;

public class WaitingReception implements Runnable {

    PostOffice postOffice;

    public WaitingReception(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    @Override
    public void run() {
        while(postOffice.getProposals().size() != postOffice.getPostMen().size()){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
