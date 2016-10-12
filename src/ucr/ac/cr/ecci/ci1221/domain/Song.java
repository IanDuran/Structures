package ucr.ac.cr.ecci.ci1221.domain;

/**
 * A simple class for representing songs. Notice that the class is immutable.
 *
 * @author Rodrigo A. Bartels
 */
public class Song implements Comparable<Song>{

    /**
     * The song title.
     */
    private String title;

    /**
     * The song's duration.
     */
    private int seconds;

    public Song(String title, int seconds){
        this.title = title;
        this.seconds = seconds;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + this.seconds + this.title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Song))
            return false;

        Song otherSong = (Song) obj;

        return this.seconds == otherSong.getSeconds() && this.title.equals(otherSong.getTitle());
    }

    @Override
    public int compareTo(Song o) {
        return this.seconds - o.getSeconds();
    }

    public String getTitle() {
        return title;
    }

    public int getSeconds() {
        return seconds;
    }
}
