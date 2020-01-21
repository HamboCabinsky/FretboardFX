import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Christian Bonin
 * @version 1/2/2020
 */
public class NoteList
{
    public Note rootNote = null;
    /**
     * Makes a doubly linked list of all 12 tones.
     */
    public NoteList(String rootTone)
    {
        String[] notes = {"C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        Note firstNote = new Note("C");
        rootNote = firstNote;
        Note currentNote = firstNote; 
        for(int i = 0; i < notes.length; i++)
        {
            currentNote.next = new Note(notes[i]);
            currentNote.next.prev = currentNote;
            currentNote = currentNote.next;
            if(notes[i].equals(rootTone)){
                rootNote = currentNote;
            }
        }
        currentNote.next = firstNote;
        firstNote.prev = currentNote;
    }
    
    /**
     * Returns an array with the notes from the minor pentatonic scale of the key the root note is on
     */
    public String[] getMinorPentatonic()
    {
        String[] minorPent = new String[5];
        int pos = 0;
        Note currentNote = rootNote;
        for(int i = 0; i < 11; i++)
        {
            if(i == 0 || i == 3 || i == 5 || i == 7 || i == 10)
            {
                minorPent[pos] = currentNote.tone;
                pos++;
            }
            currentNote = currentNote.next;
        }
        return minorPent;
    }
    
    /**
     * The major scale is just the minor scale shifted down three frets but with the root note staying the same
     * We're not returning the rootnote and the scale is unordered
     */
    public String[] getMajorPentatonic()
    {
        String[] majorPent = new String[5];
        int pos = 0;
        Note currentNote = rootNote.prev.prev.prev;
        for(int i = 0; i < 11; i++)
        {
            if(i == 0 || i == 3 || i == 5 || i == 7 || i == 10)
            {
                majorPent[pos] = currentNote.tone;
                pos++;
            }
            currentNote = currentNote.next;
        }
        return majorPent;
    }
    
    public String[] getMinor()
    {
        String[] minor = new String[7];
        int pos = 0;
        Note currentNote = rootNote;
        for(int i = 0; i < 11; i++)
        {
            if(i == 0 || i == 2 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10)
            {
                minor[pos] = currentNote.tone;
                pos++;
            }
            currentNote = currentNote.next;
        }
        return minor;
    }
    
    public String[] getMajor()
    {
        String[] major = new String[7];
        int pos = 0;
        Note currentNote = rootNote.prev.prev.prev;
        for(int i = 0; i < 11; i++)
        {
            if(i == 0 || i == 2 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10)
            {
                major[pos] = currentNote.tone;
                pos++;
            }
            currentNote = currentNote.next;
        }
        return major;
    }
    
    public void shiftRootRight()
    {
        rootNote = rootNote.next;
    }
    
    public void shiftRootLeft()
    {
        rootNote = rootNote.prev;
    }
    
    public void printNote(int position)
    {
        Note currentNote = rootNote;
        while(position > 0)
        {
            currentNote = currentNote.next;
            position--;
        }
        System.out.println(currentNote.tone);
    }
    
    public static boolean containsNote(String[] scale, String note)
    {
        for(int i = 0; i < scale.length; i++)
        {
            if(note.equals(scale[i]))
                return true;
        }
        return false;
    }
    
    public void paintNotes(String[] scale, int stringY, String key, GraphicsContext gc)
    {
        if(scale == null)
            return;
        int xPos = 5; 
        Note currentNote = rootNote;
        for(int i = 0; i < 18; i++)
        {
            if(containsNote(scale, currentNote.tone))
            {
                gc.setFill(Color.BLACK);
                if(currentNote.tone.equals(key))
                    gc.setFill(Color.RED);
                gc.fillOval(xPos, stringY, 40, 40);
                gc.setFill(Color.WHITE);
                gc.fillText(currentNote.tone,xPos+15,stringY+20);
            }
            currentNote = currentNote.next;
            xPos += 50;
        }
    }
    
    public class Note
    {
        public String tone = null;
        public Note next = null;
        public Note prev = null;
        
        public Note(String tone)
        {
            this.tone = tone;
        }
    }
}
