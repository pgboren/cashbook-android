package com.soleap.cashbook.document;

import com.soleap.cashbook.common.document.Document;

public class Phonenumber extends Document {

        private String number;

        private String type;

        private Boolean isPrimary = false;

        public Boolean getPrimary() {
            return isPrimary;
        }

        public void setPrimary(Boolean primary) {
            isPrimary = primary;
        }

    public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }