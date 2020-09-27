package net.neiquan.okhttp;


public  abstract class NetCallBack  implements NetCallBackInter {

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish() {
		
	}

	@Override
	public void onProgress(int bytesWritten, int totalSize) {
		// TODO Auto-generated method stub
		
	}

	public abstract void onSuccess(String response,String mssage,ResultModel model) ;
	
	public abstract void onFail(boolean dataError,int errorCode,String mssage) ;


}
