package pl.michalek.marcin.commitnotifier.entity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Commit implements Comparable<Commit> {
  public static final String FIELD_AUTHOR = "author";
  public static final String FIELD_NAME = "name";
  public static final String FIELD_TIMESTAMP = "date";
  public static final String FIELD_STATUS = "status";

  @Expose
  private String author;

  @Expose
  private String name;

  @Expose
  private long timestamp;

  @Expose
  private String status;

  public static Commit from(Bundle extras) {
    return new Commit(
        String.valueOf(extras.get(FIELD_AUTHOR)),
        String.valueOf(extras.get(FIELD_NAME)),
        Long.parseLong(String.valueOf(extras.get(FIELD_TIMESTAMP))),
        String.valueOf(extras.get(FIELD_STATUS))
    );
  }

  public Commit(String author, String name, long timestamp, String status) {
    this.author = author;
    this.name = name;
    this.timestamp = timestamp;
    this.status = status;
  }

  public static Commit from(String json) {
    return new Gson().fromJson(json, Commit.class);
  }

  public String toJson() {
    return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        .toJson(this);
  }

  //  GETTERS AND SETTERS
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public int compareTo(@NonNull Commit another) {
    if (timestamp < another.getTimestamp()) {
      return 1;
    } else {
      return -1;
    }
  }
}
