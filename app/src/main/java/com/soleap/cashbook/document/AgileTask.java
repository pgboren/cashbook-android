package com.soleap.cashbook.document;

import com.soleap.cashbook.common.document.BsDocument;

import java.util.HashMap;
import java.util.Map;

public class AgileTask extends BsDocument {

    private String description;
    private String stage;
    private String board;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", getName());
        data.put("description", description);
        data.put("stage", stage);
        data.put("board", board);
        return data;
    }
}
