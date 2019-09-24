/**
* <p>Title: Music.java</p>
* <p>Description: </p>
* @author 周楠
* @date 2019年9月5日
* @version 1.0
 */
package entity;

import java.util.Date;

/**
* <p>Title: Music</p>
* <p>Description: 音乐信息</p>
* @author 周楠
* @date 2019年9月5日
 */
public class Music {
	private int id;
	private String title;
	private String singer;
	private Date time;
	private String url;
	private int userid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Music [id=" + id + ", title=" + title + ", singer=" + singer + ", time=" + time + ", url=" + url
				+ ", userid=" + userid + "]";
	}
}
