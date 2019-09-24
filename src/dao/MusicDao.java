/**
* <p>Title: MusicDao.java</p>
* <p>Description: </p>
* @author 周楠
* @date 2019年9月5日
* @version 1.0
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Music;
import util.DBUtils;

/**
* <p>Title: MusicDao</p>
* <p>Description: Music的接口层
* @author 周楠
* @date 2019年9月5日
 */
public class MusicDao {
	/**
	 * 
	* <p>Title: findAllMusic</p>
	* <p>Description: </p>
	* @return
	 */
	public List<Music> findAllMusic(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Music> musics = new ArrayList<Music>();
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select * from music");
			rs = ps.executeQuery();
			while(rs.next()) {
				Music music = new Music();
				music.setId(rs.getInt("id"));
				music.setSinger(rs.getString("singer"));
				music.setTime(rs.getDate("time"));
				music.setTitle(rs.getString("title"));
				music.setUrl(rs.getString("url"));
				music.setUserid(rs.getInt("userid"));
				musics.add(music);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtils.getClosed(conn, ps, rs);
		}
		return musics;
	}
	/**
	 * 根据关键字查询歌单
	 */
	public List<Music> ifMusics(String	str){
		List<Music> musics = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select * from music where title like '%"+str+"%'");
			rs = ps.executeQuery();
			while(rs.next()) {
				Music music =new Music();
				music.setId(rs.getInt("id"));
				music.setSinger(rs.getString("singer"));
				music.setTime(rs.getDate("time"));
				music.setTitle(rs.getString("title"));
				music.setUrl(rs.getString("url"));
				music.setUserid(rs.getInt("userid"));
				musics.add(music);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtils.getClosed(conn, ps, rs);
		}
		return musics;
	}
	public static void main(String[] args) {
		MusicDao musicDao = new MusicDao();
		System.out.println(musicDao.findAllMusic());
	}
}
