package hcmute.edu.vn.leafnote.model;

public class ContactRowNote {
    private String mTittleRowNote;
    private String mContentRowNote;
    private String mDateRowNote;

    public ContactRowNote(String mTittleRowNote, String mContentRowNote, String mDateRowNote) {
        this.mTittleRowNote = mTittleRowNote;
        this.mContentRowNote = mContentRowNote;
        this.mDateRowNote = mDateRowNote;
    }

    public String getmTittleRowNote() {
        return mTittleRowNote;
    }

    public void setmTittleRowNote(String mTittleRowNote) {
        this.mTittleRowNote = mTittleRowNote;
    }

    public String getmContentRowNote() {
        return mContentRowNote;
    }

    public void setmContentRowNote(String mContentRowNote) {
        this.mContentRowNote = mContentRowNote;
    }

    public String getmDateRowNote() {
        return mDateRowNote;
    }

    public void setmDateRowNote(String mDateRowNote) {
        this.mDateRowNote = mDateRowNote;
    }
}
