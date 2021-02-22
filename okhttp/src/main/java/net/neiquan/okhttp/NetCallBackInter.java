package net.neiquan.okhttp;

public interface NetCallBackInter {

	/**
	 * start network
	 */
	public void onStart();

	/**
	 * success
	 * @param mssage
	 * @param resultBody
	 */
	public void onSuccess(String response,String mssage, ResultModel resultBody);

	/**
	 * fail
	 * @param dataError  是否是 数据错误 还是服务器错误
	 * @param errorCode  错误码
	 * @param mssage     携带的信息
	 */
	public void onFail(boolean dataError, int errorCode, String mssage) ;
	
	public void onCancel();

	public void onFinish();
	
	public void onProgress(int bytesWritten, int totalSize);

}
