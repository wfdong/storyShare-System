package jokes.controllers;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import jokes.beans.daobeans.JokeTransactionBean;
import jokes.beans.response.JokeItem;
import jokes.beans.response.Response;
import jokes.beans.response.RestfullPage;
import jokes.services.JokeService;
import jokes.services.StatisticService;
import jokes.dao.mybatismapper.UserFileMapper;
import jokes.beans.daobeans.UserFile;
import jokes.controllers.UserController;

@RestController
public class JokesController {
	
	private static Logger logger = LoggerFactory.getLogger(JokesController.class);
	
	@Autowired
	JokeService jokeService;
	
	@Autowired
    private UserController userController;
	
	@Autowired
    private UserFileMapper userFileMapper;
	
	@Autowired
	private StatisticService statisticService;

	@RequestMapping({"/jokes/index"})
	@ResponseBody
	public Response getIndexPage_jokeIndex(HttpServletRequest httpServletRequest,
			JokeTransactionBean jokeTransactionBean,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rank", required = false, defaultValue = "0") Integer rank) throws ParseException {
		return getIndexPage(httpServletRequest, jokeTransactionBean, limit, page, rank);
	}
	
	@RequestMapping({"/jokes"})
	@ResponseBody
	public Response getIndexPage_joke(HttpServletRequest httpServletRequest,
			JokeTransactionBean jokeTransactionBean,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rank", required = false, defaultValue = "0") Integer rank) throws ParseException {
		return getIndexPage(httpServletRequest, jokeTransactionBean, limit, page, rank);
	}
	
	@RequestMapping({"/"})
	@ResponseBody
	public Response getIndexPage_(HttpServletRequest httpServletRequest,
			JokeTransactionBean jokeTransactionBean,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rank", required = false, defaultValue = "0") Integer rank) throws ParseException {
		return getIndexPage(httpServletRequest, jokeTransactionBean, limit, page, rank);
	}
	
	private Response getIndexPage(HttpServletRequest httpServletRequest,
			JokeTransactionBean jokeTransactionBean,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "rank", required = false, defaultValue = "0") Integer rank) throws ParseException {
		RestfullPage restfullPage = new RestfullPage();
		logger.info("Receive {}"+httpServletRequest.getRemoteAddr(), JSON.toJSONString(jokeTransactionBean));
//		statisticService.pageStatistic(httpServletRequest, jokeTransactionBean);
		restfullPage.setCode(20000);
		restfullPage.setTotal(10);
		restfullPage.setData("........data.....");
		restfullPage.setMsg("success!");
		
		List<JokeItem> result = jokeService.getJokesPage(page, limit, rank);
		long total = jokeService.getTotalCount();
		restfullPage.setData(result);
		restfullPage.setTotal(total);
		return restfullPage;
	}
	
	@RequestMapping({"/jokes/login"})
	@ResponseBody
	public Response loginPage(HttpServletRequest httpServletRequest,
			JokeTransactionBean jokeTransactionBean) throws ParseException {
		RestfullPage restfullPage = new RestfullPage();
		logger.info("Receive {}"+httpServletRequest.getRemoteAddr(), JSON.toJSONString(jokeTransactionBean));
		restfullPage.setCode(20000);
		restfullPage.setTotal(10);
		restfullPage.setData("........login.....");
		restfullPage.setMsg("success!");
		
		return restfullPage;
	}
	
	@RequestMapping({"/jokes/logout"})
	@ResponseBody
	public Response logoutPage(HttpServletRequest httpServletRequest,
			JokeTransactionBean jokeTransactionBean) throws ParseException {
		RestfullPage restfullPage = new RestfullPage();
		
		restfullPage.setCode(20000);
		restfullPage.setTotal(10);
		restfullPage.setData("........logout.....");
		restfullPage.setMsg("success!");
		
		return restfullPage;
	}
	
	@RequestMapping({"/jokes/addJokes"})
	@PreAuthorize("hasAnyRole('admin','general','anonymous')")
	@ResponseBody
	public Response addAJoke(HttpServletRequest httpServletRequest,
			JokeTransactionBean jokeTransactionBean) throws ParseException {
        RestfullPage restfullPage = new RestfullPage();
		
        String userName = getOperatorName(httpServletRequest);
        jokeTransactionBean.setUsername(userName);
        String tid = jokeService.addNewJoke(jokeTransactionBean);
        if(null != tid) {
            restfullPage.setCode(20000);
    		restfullPage.setTotal(10);
    		restfullPage.setData("........data.....");
    		restfullPage.setMsg("success!");
        }
		return restfullPage;
	}
	
	@RequestMapping({"/jokes/addALike"})
	@PreAuthorize("hasAnyRole('admin','general','anonymous')")
	@ResponseBody
	public Response addALike(HttpServletRequest httpServletRequest,
			JokeTransactionBean jokeTransactionBean) throws ParseException {
		RestfullPage restfullPage = new RestfullPage();
		logger.info("Receive {}"+httpServletRequest.getRemoteAddr(), JSON.toJSONString(jokeTransactionBean));
		String userName = getOperatorName(httpServletRequest);
		int liked = jokeService.addALiked(jokeTransactionBean, userName);
		if(liked>=0) {
			restfullPage.setCode(20000);
    		restfullPage.setTotal(1);
    		restfullPage.setData(liked);
    		restfullPage.setMsg("success!");
		}
		return restfullPage;
	}
	
	
	@RequestMapping({"/jokes/addADisLike"})
	@PreAuthorize("hasAnyRole('admin','general','anonymous')")
	@ResponseBody
	public Response addADisLike(HttpServletRequest httpServletRequest,
			JokeTransactionBean jokeTransactionBean) throws ParseException {
		RestfullPage restfullPage = new RestfullPage();
		logger.info("Receive {}"+httpServletRequest.getRemoteAddr(), JSON.toJSONString(jokeTransactionBean));
		String userName = getOperatorName(httpServletRequest);
		int disliked = jokeService.addADisLiked(jokeTransactionBean, userName);
		if(disliked>=0) {
			restfullPage.setCode(20000);
    		restfullPage.setTotal(1);
    		restfullPage.setData(disliked);
    		restfullPage.setMsg("success!");
		}
		return restfullPage;
	}
	
	@RequestMapping({"/jokes/addComments"})
	@PreAuthorize("hasAnyRole('admin','general','anonymous')")
	@ResponseBody
	public Response addAComment(HttpServletRequest httpServletRequest,
			JokeTransactionBean jokeTransactionBean) throws ParseException {
		logger.info("Receive {}"+httpServletRequest.getRemoteAddr(), JSON.toJSONString(jokeTransactionBean));
        RestfullPage restfullPage = new RestfullPage();
        String userName = getOperatorName(httpServletRequest);
        JokeItem result = jokeService.addNewComment(jokeTransactionBean, userName);
		restfullPage.setCode(20000);
		restfullPage.setTotal(10);
		restfullPage.setData(result);
		restfullPage.setMsg("success!");
		
		return restfullPage;
	}
	
	@RequestMapping({"/jokes/getSpecificItem"})
//	@PreAuthorize("hasAnyRole('admin','general','anonymous')")
	@ResponseBody
	public Response getSpecificItem(HttpServletRequest httpServletRequest,
			JokeTransactionBean jokeTransactionBean) throws ParseException {
		logger.info("Receive {}"+httpServletRequest.getRemoteAddr(), JSON.toJSONString(jokeTransactionBean));
        RestfullPage restfullPage = new RestfullPage();
        JokeItem result = jokeService.getASpecificItem(jokeTransactionBean.getTid());
		restfullPage.setCode(20000);
		restfullPage.setTotal(10);
		restfullPage.setData(result);
		restfullPage.setMsg("success!");
		
		return restfullPage;
	}
	
	private String getOperatorName(HttpServletRequest httpServletRequest){
        UserController.UserInfo userInfo = userController.getUserInfo(httpServletRequest);
        if(userInfo.roles == null) {
            logger.error("User has no roles {}", JSON.toJSONString(userInfo));
            return null;
        }
        return userInfo.name;
    }
	
}
