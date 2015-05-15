package pl.michalek.marcin.commitnotifier.entity;

import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Commit {
  @Expose
  private String author;

  @Expose
  private String name;

  public static Commit from(Bundle extras) {
    //parse and init commit
    //@TODO
    return new Commit();
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
}
