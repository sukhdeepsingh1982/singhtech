package com.singhtech.command;

import com.singhtech.model.Path;

public interface Operation {
        public boolean evaluate(Path path, int y);
    }