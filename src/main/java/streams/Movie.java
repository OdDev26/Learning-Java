package streams;

public class Movie {
    private int likes;

    public Movie(String title) {
        this.title = title;
    }

    private String title;


    private Genre genre;

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Movie(int likes, String title,Genre genre) {
        this.likes = likes;
        this.title = title;
        this.genre= genre;
    }

    public int getLikes() {
        return likes;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "likes=" + likes +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                '}';
    }
}
