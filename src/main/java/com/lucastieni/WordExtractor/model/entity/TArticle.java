package com.lucastieni.WordExtractor.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "Article", schema = "WordExtractor")
public class TArticle {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private long Id;
	
	@Column(name = "doi")
	private String DOI;
	
	@Column(name = "Title")
	private String Title;
	
	@Column(name = "Elocate")
	private String Elocate;
	
	@Column(name = "ArticleType")
	private String ArticleType;
	
	@Column(name = "PubDay")
	private Integer PubDay;
	
	@Column(name = "PubMonth")
	private Integer PubMonth;
	
	@Column(name = "PubYear")
	private Integer PubYear;
	
	@Column(name = "ReceivedDay")
	private Integer ReceivedDay;
	
	@ManyToOne
	@JoinColumn(name = "id_journal")
	private TJournal journal;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getDOI() {
		return DOI;
	}

	public void setDOI(String dOI) {
		DOI = dOI;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getElocate() {
		return Elocate;
	}

	public void setElocate(String elocate) {
		Elocate = elocate;
	}

	public String getArticleType() {
		return ArticleType;
	}

	public void setArticleType(String articleType) {
		ArticleType = articleType;
	}

	public Integer getPubDay() {
		return PubDay;
	}

	public void setPubDay(Integer pubDay) {
		PubDay = pubDay;
	}

	public Integer getPubMonth() {
		return PubMonth;
	}

	public void setPubMonth(Integer pubMonth) {
		PubMonth = pubMonth;
	}

	public Integer getPubYear() {
		return PubYear;
	}

	public void setPubYear(Integer pubYear) {
		PubYear = pubYear;
	}

	public Integer getReceivedDay() {
		return ReceivedDay;
	}

	public void setReceivedDay(Integer receivedDay) {
		ReceivedDay = receivedDay;
	}

	public TJournal getJournal() {
		return journal;
	}

	public void setJournal(TJournal journal) {
		this.journal = journal;
	}

	@Override
	public String toString() {
		return "TArticle [Id=" + Id + ", DOI=" + DOI + ", Title=" + Title + ", Elocate=" + Elocate + ", ArticleType="
				+ ArticleType + ", PubDay=" + PubDay + ", PubMonth=" + PubMonth + ", PubYear=" + PubYear
				+ ", ReceivedDay=" + ReceivedDay + ", journal=" + journal + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ArticleType == null) ? 0 : ArticleType.hashCode());
		result = prime * result + ((DOI == null) ? 0 : DOI.hashCode());
		result = prime * result + ((Elocate == null) ? 0 : Elocate.hashCode());
		result = prime * result + (int) (Id ^ (Id >>> 32));
		result = prime * result + ((PubDay == null) ? 0 : PubDay.hashCode());
		result = prime * result + ((PubMonth == null) ? 0 : PubMonth.hashCode());
		result = prime * result + ((PubYear == null) ? 0 : PubYear.hashCode());
		result = prime * result + ((ReceivedDay == null) ? 0 : ReceivedDay.hashCode());
		result = prime * result + ((Title == null) ? 0 : Title.hashCode());
		result = prime * result + ((journal == null) ? 0 : journal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TArticle other = (TArticle) obj;
		if (ArticleType == null) {
			if (other.ArticleType != null)
				return false;
		} else if (!ArticleType.equals(other.ArticleType))
			return false;
		if (DOI == null) {
			if (other.DOI != null)
				return false;
		} else if (!DOI.equals(other.DOI))
			return false;
		if (Elocate == null) {
			if (other.Elocate != null)
				return false;
		} else if (!Elocate.equals(other.Elocate))
			return false;
		if (Id != other.Id)
			return false;
		if (PubDay == null) {
			if (other.PubDay != null)
				return false;
		} else if (!PubDay.equals(other.PubDay))
			return false;
		if (PubMonth == null) {
			if (other.PubMonth != null)
				return false;
		} else if (!PubMonth.equals(other.PubMonth))
			return false;
		if (PubYear == null) {
			if (other.PubYear != null)
				return false;
		} else if (!PubYear.equals(other.PubYear))
			return false;
		if (ReceivedDay == null) {
			if (other.ReceivedDay != null)
				return false;
		} else if (!ReceivedDay.equals(other.ReceivedDay))
			return false;
		if (Title == null) {
			if (other.Title != null)
				return false;
		} else if (!Title.equals(other.Title))
			return false;
		if (journal == null) {
			if (other.journal != null)
				return false;
		} else if (!journal.equals(other.journal))
			return false;
		return true;
	}
}
