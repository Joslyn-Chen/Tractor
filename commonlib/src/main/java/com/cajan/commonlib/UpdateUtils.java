package com.cajan.commonlib;

/**
 * ClassName ：UpdateUtils
 * Description ：更新工具
 * Created : Cajan
 * Time : 2015/12/22
 * Version : 1.0
 */
public class UpdateUtils {

    /**
     * 1、获取版本信息
     * 2、对比版本信息
     * 3、Dialog提示是否更新（提示版本信息）
     * 4、更新：下载
     * 5、更新完成：安装
     */
    /*private static Context mContext;
    private static UpdateUtils updateUtils;
    private AppHttpClient mAppHttpClient;

    private static final int DIALOG_TYPE_LATEST = 0;
    private static final int DIALOG_TYPE_FAIL = -1;
    private static final int DOWN_OVER = 1;
    private static final int DOWN_ERROR = 2;
    private static final int DOWN_UPDATE = 3;
    private static final int DOWN_VERSION = 4;

    *//**
     * 通知对话框
     *//*
    private Dialog noticeDialog;
    *//**
     * 下载对话框
     *//*
    private Dialog downloadDialog;
    *//**
     * 检测对话框
     *//*
    private Dialog latestOrFailDialog;
    *//**
     * 查询动画
     *//*
    private ProgressDialog mProDialog;
    *//** 显示下载百分比 *//*
//    private TextView mProgressText;
    *//**
     * 进度条
     *//*
    private NumberProgressBar mProgress;
    *//**
     * 进度值
     *//*
    private int progress;
    *//**
     * 下载线程
     *//*
    private Thread downLoadThread;
    *//**
     * 终止标记
     *//*
    private boolean interceptFlag;
    *//**
     * 下载包保存路径
     *//*
    private String savePath = "";
    *//**
     * apk保存完整路径
     *//*
    private String apkFilePath = "";
    *//**
     * 临时下载文件路径
     *//*
    private String tmpFilePath = "";
    *//**
     * 更新内容
     *//*
    private String updateMsg = "";
    *//**
     * 当前版本号
     *//*
    private String versionNumber = "";
    *//**
     * 新版本号
     *//*
    private String newVersion = "";
    *//** 当前包名 *//*
//	private String packageName = "";
    *//**
     * 是否手动更新
     *//*
    private boolean isHandUpdate = false;
    *//**
     * 新版本信息
     *//*
    //private AppInfoEntity mInfoEntity;
    *//**
     * 下载地址
     *//*
    private String downUrl;

    private final UpdateHandler mHandler = new UpdateHandler(this);

    static class UpdateHandler extends Handler {

        WeakReference<UpdateUtils> mWeakReference;

        public UpdateHandler(UpdateUtils updateUtils) {
            mWeakReference = new WeakReference<>(updateUtils);
        }

        @Override
        public void handleMessage(Message msg) {
            UpdateUtils updateUtils = mWeakReference.get();
            switch (msg.what) {
                case DOWN_UPDATE:
                    updateUtils.mProgress.setProgress((int) msg.obj);
                    break;
                case DOWN_OVER:
                    if (updateUtils.downloadDialog != null) {
                        updateUtils.downloadDialog.dismiss();
                    }
                    updateUtils.installApk();
                    break;
                case DOWN_ERROR:
                    if (updateUtils.downloadDialog != null) {
                        updateUtils.downloadDialog.dismiss();
                    }
                    if (updateUtils.isHandUpdate) {
                        ToastUtil.showToast(mContext, "更新失败！");
                    }
                    break;
                case DOWN_VERSION:
                    updateUtils.checkResults();
                    break;
            }

        }
    }

    public static UpdateUtils getInstance(Context context) {
        UpdateUtils.mContext = context;
        if (updateUtils == null) {
            updateUtils = new UpdateUtils();
        }
        updateUtils.interceptFlag = false;
        updateUtils.mAppHttpClient = new AppHttpClient();
        return updateUtils;
    }

    *//**
     * 手动检查App更新
     *//*
    public void handCheckAppUpdate() {
        if (AppUtils.isNetworkAvailable(mContext)) {
            getCurrentVersion();
            // 显示检测结果
            isHandUpdate = true;
            if (mProDialog == null) {
                mProDialog = ProgressDialog.show(mContext, null, "正在检测，请稍后...", true, true);
            } else if (mProDialog.isShowing()
                    || (latestOrFailDialog != null && latestOrFailDialog
                    .isShowing())) {
                return;
            }
            downloadAPPInfo();
        } else {
            showLatestOrFailDialog(DIALOG_TYPE_FAIL);
        }
    }

    *//**
     * 检测版本结果
     *//*
    private void checkResults() {
        // 进度条对话框不显示 - 检测结果也不显示
        if (mProDialog != null && !mProDialog.isShowing()) {
            return;
        }
        // 关闭并释放释放进度条对话框
        if (mProDialog != null) {
            mProDialog.dismiss();
            mProDialog = null;
        }
        // 显示检测结果
        if (AppUtils.isNetworkAvailable(mContext) && !StringUtils.isEmpty(newVersion)) {
            if (newVersion.equals(versionNumber)) {
                if (isHandUpdate) {
                    isHandUpdate = false;
                    showLatestOrFailDialog(DIALOG_TYPE_LATEST);
                }
            } else {
                // 有更新把原来的版本删除
                String appName = mContext.getResources().getString(R.string.app_name);
                String apkName = appName + ".apk";
                String apkFilePath = FileManageUtils.getFileDownloadDir(mContext) + apkName;
                File ApkFile = new File(apkFilePath);
                if (ApkFile.exists()) {
                    FileManageUtils.deleteFile(ApkFile);
                }
                showNoticeDialog();
            }
        } else {
            showLatestOrFailDialog(DIALOG_TYPE_FAIL);
        }
    }

    *//**
     * 获取当前客户端版本信息
     *//*
    private void getCurrentVersion() {
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            versionNumber = info.versionName;
            LogUtil.e(versionNumber);
        } catch (PackageManager.NameNotFoundException e) {
        }
    }

    *//**
     * 显示版本更新通知对话框
     *//*
    private void showNoticeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("版本更新！");
        builder.setMessage("当前版本：" + versionNumber + "\n" + "最新版本："
                + newVersion + "\n" + updateMsg);
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                showDownloadDialog();
            }
        });
        builder.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        noticeDialog = builder.create();
        noticeDialog.show();
    }

    *//**
     * 自动检测App更新
     *//*
    public void autoCheckAppUpdate() {
        getCurrentVersion();
        downloadAPPInfo();
    }

    *//**
     * 下载APP更新信息
     *//*
    private void downloadAPPInfo() {
        mAppHttpClient.getAppInfo(new MyCallback<HttpResponse<AppInfoEntity>>() {
            @Override
            public void onBefore() {
            }

            @Override
            public void onSuccess(final HttpResponse<AppInfoEntity> response) {
                LogUtil.e(response);
                if (response.isSuccess()) {
                    AppInfoEntity infoEntity = response.getData();
                    newVersion = infoEntity.getVersion();
                    downUrl = infoEntity.getUrl();
                    updateMsg = infoEntity.getRank();
                    mHandler.sendEmptyMessage(DOWN_VERSION);
                } else {
                    onFailure(new Throwable());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mHandler.sendEmptyMessage(DOWN_ERROR);
            }

            @Override
            public void onAfter() {
            }
        });
    }


    *//**
     * 显示'已经是最新'或者'无法获取版本信息'对话框
     *//*
    private void showLatestOrFailDialog(int dialogType) {
        if (latestOrFailDialog != null) {
            // 关闭并释放之前的对话框
            latestOrFailDialog.dismiss();
            latestOrFailDialog = null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("提示！");
        if (dialogType == DIALOG_TYPE_LATEST) {
            builder.setMessage("当前版本：" + versionNumber + "\n" + "已经是最新版本");
        } else if (dialogType == DIALOG_TYPE_FAIL) {
            builder.setMessage("当前网络无法获取新版本");
        }
        builder.setPositiveButton("确定", null);
        latestOrFailDialog = builder.create();
        latestOrFailDialog.show();
    }

    *//**
     * 显示下载对话框
     *//*
    private void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("正在下载……");
        builder.setMessage("新版本：" + newVersion);
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View updateView = inflater.inflate(R.layout.layout_update_progress, null);
        mProgress = (NumberProgressBar) updateView.findViewById(R.id.update_progress);
        mProgress.setMax(100);
        builder.setView(updateView);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                interceptFlag = true;
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
                interceptFlag = true;
            }
        });
        builder.setCancelable(false);
        downloadDialog = builder.create();
        downloadDialog.setCanceledOnTouchOutside(false);
        downloadDialog.show();

        downloadApk();
    }

    *//**
     * 下载apk
     *//*
    private void downloadApk() {
        downLoadThread = new Thread(downLoadRunnable);
        downLoadThread.start();
    }

    private Runnable downLoadRunnable = new Runnable() {

        @Override
        public void run() {
            try {
                startdownload();
            } catch (IOException e) {
                mHandler.sendEmptyMessage(DOWN_ERROR);
            }
        }

    };

    *//***
     * 创建文件
     *//*
    private void createFile() {
        String appName = mContext.getResources().getString(R.string.app_name);
        String apkName = appName + ".apk";
        String tmpApk = appName + ".tmp";
        // 判断是否挂载了SD卡
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            savePath = FileManageUtils.getFileDownloadDir(mContext);
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            apkFilePath = savePath + apkName;
            tmpFilePath = savePath + tmpApk;
        }

        // 没有挂载SD卡，无法下载文件
        if (StringUtils.isEmpty(apkFilePath)) {
            mHandler.sendEmptyMessage(DOWN_ERROR);
        }
    }

    private void startdownload() throws IOException {
        createFile();
        // APP下载URL
        File ApkFile = new File(apkFilePath);

        // 是否已下载更新文件
        if (ApkFile.exists()) {
            downloadDialog.dismiss();
            installApk();
            return;
        }

        // 输出临时下载文件
        File tmpFile = new File(tmpFilePath);
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            fos = new FileOutputStream(tmpFile);

            URL url = new URL(downUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            int length = conn.getContentLength();
            is = conn.getInputStream();

            int count = 0;
            byte buf[] = new byte[1024];

            do {
                int numread = is.read(buf);
                count += numread;
                // 当前进度值
                progress = (int) (((float) count / length) * 100);
                // 更新进度
                mHandler.sendMessage(mHandler.obtainMessage(DOWN_UPDATE, progress));
                if (numread <= 0) {
                    // 下载完成 - 将临时下载文件转成APK文件
                    if (tmpFile.renameTo(ApkFile)) {
                        // 通知安装
                        mHandler.sendEmptyMessage(DOWN_OVER);
                    }
                    break;
                }
                fos.write(buf, 0, numread);
            } while (!interceptFlag);// 点击取消就停止下载
        } catch (Exception e) {
            mHandler.sendEmptyMessage(DOWN_ERROR);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }
    }


    *//**
     * 安装apk
     *//*
    private void installApk() {
        File apkfile = new File(apkFilePath);
        if (!apkfile.exists()) {
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }*/

}
