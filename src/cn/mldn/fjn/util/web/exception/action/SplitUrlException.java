package cn.mldn.fjn.util.web.exception.action;

import cn.mldn.fjn.util.web.exception.DispatcherException;

@SuppressWarnings("serial")
public class SplitUrlException extends DispatcherException{
	public SplitUrlException(String msg) {
		super(msg);
	}

}
