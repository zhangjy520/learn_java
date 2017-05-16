package cc.gukeer.support.analysis.entity;

/**
 * Created by lx on 2016/12/5.
 */
public class LeleInfoPart {
    private String term;
    private String  chapter ;
    private String section;
    private String  chip;

    public void setTerm(String term) {
        this.term = term;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getTerm() {

        return term;
    }

    public String getChapter() {
        return chapter;
    }

    public String getSection() {
        return section;
    }

    public String getChip() {
        return chip;
    }
}
