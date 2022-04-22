package hcmute.edu.vn.leafnote;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class NoteSlideTabActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_slide_tab);

        addControl();
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new TextNoteFragment(), "Văn bản");
        viewPagerAdapter.addFragment(new PhotoNoteFragment(), "Hình ảnh");
        viewPagerAdapter.addFragment(new VideoNoteFragment(), "Quay phim");
        viewPagerAdapter.addFragment(new DrawNoteFragment(), "Phát thảo");
        viewPagerAdapter.addFragment(new AttachNoteFragment(), "Đính kèm");
        viewPagerAdapter.addFragment(new RecordNoteFragment(), "Bản ghi âm");

        viewPager.setAdapter(viewPagerAdapter);
    }

    private void addControl() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }
}