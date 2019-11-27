package com.beetech.kayak.bus_event;

import jp.co.panasonic.pstc.ocr.card.CardRecog;

public class CardRecognizeSuccessEvent {
    private CardRecog recog;

    public CardRecognizeSuccessEvent(CardRecog recog) {
        this.recog = recog;
    }

    public CardRecog getRecog() {
        return recog;
    }

    public void setRecog(CardRecog recog) {
        this.recog = recog;
    }
}
