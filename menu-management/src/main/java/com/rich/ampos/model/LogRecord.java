package com.rich.ampos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "log")
public class LogRecord {

    @Id
    private String id;
    private String msg;
    private int status;

    public LogRecord(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }
}
