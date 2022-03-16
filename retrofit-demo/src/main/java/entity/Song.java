package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Song {
    private int id;
    private int account_id;
    private String name;
    private String singer;
    private String author;
    private String thumbnail;
    private String link;
    private String message;
    private String created_at;
    private String updated_at;
    private int status;
}
