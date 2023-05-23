package com.soleap.cashbook.common.widget.dialog;

import android.view.View;

public abstract class DialogViewHolder<V> {

        protected View itemView;

        public DialogViewHolder(View itemView) {
            this.itemView = itemView;
        }

        protected abstract void onBind(V value);

        public abstract V readValue();
    }