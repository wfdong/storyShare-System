package jokes.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;

import jokes.beans.daobeans.JokeTable;
import jokes.beans.daobeans.JokeTransactionBean;
import jokes.beans.daobeans.UserFile;
import jokes.beans.response.JokeItem;
import jokes.dao.mybatismapper.JokeCommentsMapper;
import jokes.dao.mybatismapper.JokeLikedMapper;
import jokes.dao.mybatismapper.JokeTableMapper;
import jokes.dao.mybatismapper.UserFileMapper;
import java.util.UUID;

import static jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.*;
import static jokes.dao.mybatismapper.JokeCommentsDynamicSqlSupport.*;
import static jokes.dao.mybatismapper.UserFileDynamicSqlSupport.*;
import static jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.*;
//import the SQL builder
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Component
public class JokeService {

	@Autowired
	JokeTableMapper jokeTableMapper;

	@Autowired
	JokeCommentsMapper jokeCommentsMapper;

	@Autowired
	UserFileMapper userFileMapper;

	@Autowired
	JokeLikedMapper jokeLikedMapper;

	public String addNewJoke(JokeTransactionBean jokeTransactionBean) {
		if (null != jokeTransactionBean && jokeTransactionBean.getUsername() != null) {
			JokeTable record = new JokeTable();
			record.setCommentsnum(0);
			record.setContent(jokeTransactionBean.getContent());
			record.setLiked(0);
			record.setDisliked(0);
			// record.setFilelocation(jokeTransactionBean.getFilelocation());
			record.setUsername(jokeTransactionBean.getUsername());
			record.setUploadtime(new Date());
			Date today = new Date();
			String tid = today.getYear() + "_" + today.getDate() + "_" + UUID.randomUUID().toString();
			record.setTid(tid);
			InsertStatementProvider<JokeTable> insertStatement = insert(record).into(jokeTable)
					.map(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.tid).toProperty("tid")
					.map(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.username).toProperty("username")
					.map(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.commentsnum).toProperty("commentsnum")
					.map(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.content).toProperty("content")
					.map(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.disliked).toProperty("disliked")
					.map(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.liked).toProperty("liked")
					// .map(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.filelocation).toProperty("filelocation")
					.map(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.uploadtime).toProperty("uploadtime").build()
					.render(RenderingStrategies.MYBATIS3);
			if (jokeTableMapper.insert(insertStatement) > 0) {
				String filelocations = jokeTransactionBean.getFilelocation();
				if (null != filelocations) {
					String[] fileLocationArray = filelocations.split("\\|");
					for (String curLocation : fileLocationArray) {
						commitOneFile(tid, "JOKE_IMAGES", curLocation);
					}
				}
				return tid;
			}
		}
		return null;
	}

	public int addALiked(JokeTransactionBean jokeTransactionBean, String operatorUsername) {
		if (null != jokeTransactionBean && jokeTransactionBean.getLiked() > 0) {
			String currentTid = jokeTransactionBean.getTid();
			String userName = jokeTransactionBean.getUsername();
			SelectStatementProvider selectStatement = select(tid,
					jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.liked).from(jokeTable)
							.where(tid, isEqualTo(currentTid))
							.and(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.username, isEqualTo(userName))
							.limit(1).build().render(RenderingStrategies.MYBATIS3);
			List<JokeTable> selectResult = jokeTableMapper.selectMany(selectStatement);
			if (null != selectResult && selectResult.size() == 1) {
				int currentLiked = selectResult.get(0).getLiked();
				int expectedLiked = currentLiked;

				// if not liked before, add liked; if liked before , remove liked
				SelectStatementProvider jokeLikedselectStatement = select(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.liked).from(jokeLiked)
						.where(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.joketid, isEqualTo(currentTid))
						.and(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.username, isEqualTo(operatorUsername)).limit(1)
						.build().render(RenderingStrategies.MYBATIS3);
				List<jokes.beans.daobeans.JokeLiked> JokeLiedList = jokeLikedMapper.selectMany(jokeLikedselectStatement);
				if(null == JokeLiedList || JokeLiedList.isEmpty() || JokeLiedList.size() < 1) {
					//insert new
					jokes.beans.daobeans.JokeLiked newLikedRecord = new jokes.beans.daobeans.JokeLiked();
					newLikedRecord.setJoketid(currentTid);
					newLikedRecord.setUsername(operatorUsername);
					newLikedRecord.setLiked(1);
					newLikedRecord.setUpdatetime(new Date());
					InsertStatementProvider<jokes.beans.daobeans.JokeLiked> jokeLikedinsertStatement = insert(newLikedRecord).into(jokeLiked)
							.map(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.joketid).toProperty("joketid")
							.map(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.username).toProperty("username")
							.map(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.liked).toProperty("liked")
							.map(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.updatetime).toProperty("updatetime").build()
							.render(RenderingStrategies.MYBATIS3);
					jokeLikedMapper.insert(jokeLikedinsertStatement);
					expectedLiked = expectedLiked + 1;
				}else{
					//update
					if(JokeLiedList.get(0).getLiked()==0) {
						//add like
						UpdateStatementProvider jokeLiedupdateStatement = update(jokeLiked).set(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.liked)
								.equalTo(1).set(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.updatetime).equalTo(new Date())
								.where(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.joketid, isEqualTo(currentTid))
								.and(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.username, isEqualTo(operatorUsername))
								.build().render(RenderingStrategies.MYBATIS3);
						jokeLikedMapper.update(jokeLiedupdateStatement);
						expectedLiked = expectedLiked + 1;
					}else {
						//remove like
						UpdateStatementProvider jokeLiedupdateStatement = update(jokeLiked).set(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.liked)
								.equalTo(0).set(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.updatetime).equalTo(new Date())
								.where(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.joketid, isEqualTo(currentTid))
								.and(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.username, isEqualTo(operatorUsername))
								.build().render(RenderingStrategies.MYBATIS3);
						jokeLikedMapper.update(jokeLiedupdateStatement);
						expectedLiked = expectedLiked - 1;
					}
				}
				
				UpdateStatementProvider updateStatement = update(jokeTable)
						.set(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.liked).equalTo(expectedLiked)
						.where(tid, isEqualTo(currentTid))
						.and(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.username, isEqualTo(userName)).build()
						.render(RenderingStrategies.MYBATIS3);
				if (jokeTableMapper.update(updateStatement) > 0) {
					return expectedLiked;
				} else {
					return currentLiked;
				}
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public int addADisLiked(JokeTransactionBean jokeTransactionBean, String operatorUsername) {
		if (null != jokeTransactionBean && jokeTransactionBean.getDisliked() > 0) {
			String currentTid = jokeTransactionBean.getTid();
			String userName = jokeTransactionBean.getUsername();
			SelectStatementProvider selectStatement = select(tid,
					jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.disliked).from(jokeTable)
							.where(tid, isEqualTo(currentTid))
							.and(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.username, isEqualTo(userName))
							.limit(1).build().render(RenderingStrategies.MYBATIS3);
			List<JokeTable> selectResult = jokeTableMapper.selectMany(selectStatement);
			if (null != selectResult && selectResult.size() == 1) {
				int currentDisLiked = selectResult.get(0).getDisliked();
				int expectedDisLiked = currentDisLiked;
				
				// if not disliked before, add disliked; if disliked before , remove disliked
				SelectStatementProvider jokeLikedselectStatement = select(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.disliked).from(jokeLiked)
						.where(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.joketid, isEqualTo(currentTid))
						.and(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.username, isEqualTo(operatorUsername)).limit(1)
						.build().render(RenderingStrategies.MYBATIS3);
				List<jokes.beans.daobeans.JokeLiked> JokeLiedList = jokeLikedMapper.selectMany(jokeLikedselectStatement);
				if(null == JokeLiedList || JokeLiedList.isEmpty() || JokeLiedList.size() < 1) {
					//insert new
					jokes.beans.daobeans.JokeLiked newLikedRecord = new jokes.beans.daobeans.JokeLiked();
					newLikedRecord.setJoketid(currentTid);
					newLikedRecord.setUsername(operatorUsername);
					newLikedRecord.setDisliked(1);
					newLikedRecord.setUpdatetime(new Date());
					InsertStatementProvider<jokes.beans.daobeans.JokeLiked> jokeLikedinsertStatement = insert(newLikedRecord).into(jokeLiked)
							.map(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.joketid).toProperty("joketid")
							.map(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.username).toProperty("username")
							.map(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.disliked).toProperty("disliked")
							.map(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.updatetime).toProperty("updatetime").build()
							.render(RenderingStrategies.MYBATIS3);
					jokeLikedMapper.insert(jokeLikedinsertStatement);
					expectedDisLiked = expectedDisLiked + 1;
				}else{
					//update
					if(JokeLiedList.get(0).getDisliked()==0) {
						//add dislike
						UpdateStatementProvider jokeLiedupdateStatement = update(jokeLiked).set(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.disliked)
								.equalTo(1).set(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.updatetime).equalTo(new Date())
								.where(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.joketid, isEqualTo(currentTid))
								.and(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.username, isEqualTo(operatorUsername))
								.build().render(RenderingStrategies.MYBATIS3);
						jokeLikedMapper.update(jokeLiedupdateStatement);
						expectedDisLiked = expectedDisLiked + 1;
					}else {
						//remove dislike
						UpdateStatementProvider jokeLiedupdateStatement = update(jokeLiked).set(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.disliked)
								.equalTo(0).set(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.updatetime).equalTo(new Date())
								.where(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.joketid, isEqualTo(currentTid))
								.and(jokes.dao.mybatismapper.JokeLikedDynamicSqlSupport.username, isEqualTo(operatorUsername))
								.build().render(RenderingStrategies.MYBATIS3);
						jokeLikedMapper.update(jokeLiedupdateStatement);
						expectedDisLiked = expectedDisLiked - 1;
					}
				}
				
				UpdateStatementProvider updateStatement = update(jokeTable)
						.set(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.disliked).equalTo(expectedDisLiked)
						.where(tid, isEqualTo(currentTid))
						.and(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.username, isEqualTo(userName)).build()
						.render(RenderingStrategies.MYBATIS3);
				if (jokeTableMapper.update(updateStatement) > 0) {
					return expectedDisLiked;
				} else {
					return currentDisLiked;
				}
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public int commitOneFile(String tid, String key, String filePath) {
		UserFile uploadFile = new UserFile();
		uploadFile.setForeignId(tid);
		uploadFile.setForeignKey(key);
		// 前端直接返回之前传输的相对路径
		uploadFile.setFilePath(filePath);
		uploadFile.setGmtCreated(new Date());
		uploadFile.setGmtModified(new Date());
		InsertStatementProvider<UserFile> insertStatement = insert(uploadFile).into(userFile)
				.map(jokes.dao.mybatismapper.UserFileDynamicSqlSupport.foreignId).toProperty("foreignId")
				.map(jokes.dao.mybatismapper.UserFileDynamicSqlSupport.filePath).toProperty("filePath")
				.map(jokes.dao.mybatismapper.UserFileDynamicSqlSupport.foreignKey).toProperty("foreignKey")
				.map(jokes.dao.mybatismapper.UserFileDynamicSqlSupport.gmtCreated).toProperty("gmtCreated")
				.map(jokes.dao.mybatismapper.UserFileDynamicSqlSupport.gmtModified).toProperty("gmtModified").build()
				.render(RenderingStrategies.MYBATIS3);
		return userFileMapper.insert(insertStatement);
	}

	/*
	 * orderby:0->commentsnum; 1->uploadtime
	 */
	public List<JokeItem> getJokesPage(int page, int num, int orderby) {
		List<JokeItem> result = new ArrayList<JokeItem>();
		if (orderby == 0) {
			SelectStatementProvider selectStatement = select(tid,
					jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.username, filelocation,
					jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.liked,
					jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.disliked, commentsnum, uploadtime, content)
							.from(jokeTable).orderBy(uploadtime.descending()).limit(num).offset((page - 1) * num)
							.build().render(RenderingStrategies.MYBATIS3);
			List<JokeTable> jokeTablesList = jokeTableMapper.selectMany(selectStatement);
			return getRelatedFilesAndComments(jokeTablesList);
		} else if (orderby == 1) {
			SelectStatementProvider selectStatement = select(tid,
					jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.username, filelocation,
					jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.liked,
					jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.disliked, commentsnum, uploadtime, content)
							.from(jokeTable).orderBy(commentsnum.descending()).limit(num).offset((page - 1) * num).build()
							.render(RenderingStrategies.MYBATIS3);
			List<JokeTable> jokeTablesList = jokeTableMapper.selectMany(selectStatement);
			return getRelatedFilesAndComments(jokeTablesList);
		}
		return result;
	}

	private List<JokeItem> getRelatedFilesAndComments(List<JokeTable> jokeTablesList) {
		if (null == jokeTablesList || jokeTablesList.isEmpty()) {
			return null;
		}
		List<JokeItem> result = new ArrayList<JokeItem>();
		for (JokeTable currentJoke : jokeTablesList) {
			JokeItem currentItem = new JokeItem();
			// get files
			SelectStatementProvider selectStatementFiles = select(jokes.dao.mybatismapper.UserFileDynamicSqlSupport.id,
					jokes.dao.mybatismapper.UserFileDynamicSqlSupport.foreignId,
					jokes.dao.mybatismapper.UserFileDynamicSqlSupport.foreignKey,
					jokes.dao.mybatismapper.UserFileDynamicSqlSupport.filePath,
					jokes.dao.mybatismapper.UserFileDynamicSqlSupport.gmtCreated,
					jokes.dao.mybatismapper.UserFileDynamicSqlSupport.gmtModified).from(userFile)
							.where(foreignId, isEqualTo(currentJoke.getTid())).limit(9).build()
							.render(RenderingStrategies.MYBATIS3);
			List<UserFile> userFiles = userFileMapper.selectMany(selectStatementFiles);
			currentItem.setUserFiles(userFiles);

			// get comments
			currentItem.setJokeTable(currentJoke);
			SelectStatementProvider selectStatement = select(commentid,
					jokes.dao.mybatismapper.JokeCommentsDynamicSqlSupport.joketid,
					jokes.dao.mybatismapper.JokeCommentsDynamicSqlSupport.username,
					jokes.dao.mybatismapper.JokeCommentsDynamicSqlSupport.updatetime, comment)
							.from(jokeComments)
							.where(jokes.dao.mybatismapper.JokeCommentsDynamicSqlSupport.joketid,
									isEqualTo(currentJoke.getTid()))
							.orderBy(jokes.dao.mybatismapper.JokeCommentsDynamicSqlSupport.updatetime).build()
							.render(RenderingStrategies.MYBATIS3);
			List<jokes.beans.daobeans.JokeComments> comments = jokeCommentsMapper.selectMany(selectStatement);
			currentItem.setJokeComments(comments);
			result.add(currentItem);
		}
		return result;
	}

	public long getTotalCount() {
		SelectStatementProvider selectStatement = select(count(tid)).from(jokeTable).limit(1).build()
				.render(RenderingStrategies.MYBATIS3);
		long result = jokeTableMapper.count(selectStatement);
		return result;
	}
	
	public JokeItem addNewComment(JokeTransactionBean jokeTransactionBean, String operatorUsername) {
		jokes.beans.daobeans.JokeComments newcommentRecord = new jokes.beans.daobeans.JokeComments();
		Date today = new Date();
		String commentid = today.getYear() + "_" + today.getDate() + "_" + UUID.randomUUID().toString();
		newcommentRecord.setCommentid(commentid);
		newcommentRecord.setJoketid(jokeTransactionBean.getTid());
		newcommentRecord.setUsername(operatorUsername);
		newcommentRecord.setComment(jokeTransactionBean.getNewAddedComment());
		newcommentRecord.setUpdatetime(new Date());
		InsertStatementProvider<jokes.beans.daobeans.JokeComments> insertStatement = insert(newcommentRecord).into(jokeComments)
				.map(jokes.dao.mybatismapper.JokeCommentsDynamicSqlSupport.commentid).toProperty("commentid")
				.map(jokes.dao.mybatismapper.JokeCommentsDynamicSqlSupport.joketid).toProperty("joketid")
				.map(jokes.dao.mybatismapper.JokeCommentsDynamicSqlSupport.username).toProperty("username")
				.map(jokes.dao.mybatismapper.JokeCommentsDynamicSqlSupport.comment).toProperty("comment")
				.map(jokes.dao.mybatismapper.JokeCommentsDynamicSqlSupport.updatetime).toProperty("updatetime")
				.build()
				.render(RenderingStrategies.MYBATIS3);
		jokeCommentsMapper.insert(insertStatement);
		return getASpecificItem_forAddComment(jokeTransactionBean.getTid());
	}
	
	public JokeItem getASpecificItem_forAddComment(String jokeTableTid) {
		SelectStatementProvider selectStatement = select(tid,
				jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.username, filelocation,
				jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.liked,
				jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.disliked, commentsnum, uploadtime, content)
						.from(jokeTable).where(tid, isEqualTo(jokeTableTid)).orderBy(uploadtime.descending()).limit(1)
						.build().render(RenderingStrategies.MYBATIS3);
		List<JokeTable> jokeTablesList = jokeTableMapper.selectMany(selectStatement);
		if(null != jokeTablesList && !jokeTablesList.isEmpty()) {
			List<JokeItem> jokeItems = getRelatedFilesAndComments(jokeTablesList);
			if(null != jokeItems && !jokeItems.isEmpty()) {
				if(null != jokeItems.get(0).getJokeTable()) {
					int currentCommentSum = jokeItems.get(0).getJokeTable().getCommentsnum();
					UpdateStatementProvider updateStatement = update(jokeTable).set(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.commentsnum).equalTo(currentCommentSum+1)
							.where(jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.tid, isEqualTo(jokeTableTid)).build()
							.render(RenderingStrategies.MYBATIS3);
					jokeTableMapper.update(updateStatement);
				}
				return jokeItems.get(0);
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	public JokeItem getASpecificItem(String jokeTableTid) {
		SelectStatementProvider selectStatement = select(tid,
				jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.username, filelocation,
				jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.liked,
				jokes.dao.mybatismapper.JokeTableDynamicSqlSupport.disliked, commentsnum, uploadtime, content)
						.from(jokeTable).where(tid, isEqualTo(jokeTableTid)).orderBy(uploadtime.descending()).limit(1)
						.build().render(RenderingStrategies.MYBATIS3);
		List<JokeTable> jokeTablesList = jokeTableMapper.selectMany(selectStatement);
		if(null != jokeTablesList && !jokeTablesList.isEmpty()) {
			List<JokeItem> jokeItems = getRelatedFilesAndComments(jokeTablesList);
			if(null != jokeItems && !jokeItems.isEmpty()) {
				return jokeItems.get(0);
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

//	public static void main(String[] args) {
//		"2020-04-18\\jennifer-5e99cdaf6f6ba17a38e0164b-4k-wallpaper-6.jpg|2020-04-18\\jennifer-5e99cdb26f6ba17a38e0164c-208156-top-4k-resolution-wallpaper-1920x1200.jpg".split("\\|");
//	}
}
