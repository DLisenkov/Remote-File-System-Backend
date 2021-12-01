package models;

import java.util.Date;
import java.util.List;

public class File {
    private int id;
    private String name;
    private String type;
    private double size;
    private Date creationDate;
    private Date modificationDate;
    private String content;
    private String directoryName;
    private int ownerId;
    private List<Integer> access;
}