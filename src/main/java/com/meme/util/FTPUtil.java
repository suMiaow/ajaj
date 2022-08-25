package com.meme.util;

import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * FTP工具类
 *
 * @author 席晨
 */
public class FTPUtil {

    private FTPUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * FTP连接池
     */
    private static final Map<String, FTPClient> connectionPool = new HashMap<>();

    /**
     * 读取文件字节数据
     *
     * @param url      文件地址
     * @param username 用户名
     * @param password 密码
     * @return 文件字节数据
     * @throws IOException 读取失败
     */
    public static String read(String url, String username, String password) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream(url, username, password)))) {

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
    }

    /**
     * 获取输入流
     *
     * @param url      文件地址
     * @param username 用户名
     * @param password 密码
     * @return 输入流
     * @throws IOException 获取流失败
     */
    public static InputStream getInputStream(String url, String username, String password) throws IOException {

        FTPFileInfo ftpFileInfo = parseFTPFileInfo(url);

        FTPClient ftpClient = connect(ftpFileInfo, username, password);

        ftpClient.changeWorkingDirectory(ftpFileInfo.getDirectory());
        ftpClient.enterLocalPassiveMode();

        return ftpClient.retrieveFileStream(ftpFileInfo.getFileName());
    }

    /**
     * 获取FTP连接
     *
     * @param ftpFileInfo FTP文件信息
     * @param username    用户名
     * @param password    密码
     * @return FTP连接
     * @throws IOException 获取连接失败
     */
    private static FTPClient connect(FTPFileInfo ftpFileInfo, String username, String password) throws IOException {

        String connectionId = username + "@" + ftpFileInfo.getHostName() + ":" + ftpFileInfo.getPort();
        FTPClient ftpClient = connectionPool.get(connectionId); // NOSONAR
        if (ftpClient == null) {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpFileInfo.getHostName(), ftpFileInfo.getPort());
            ftpClient.login(username, password);
            connectionPool.put(connectionId, ftpClient);
        }

        return ftpClient;
    }

    /**
     * 从url解析FTP文件信息
     *
     * @param url FTP文件url
     * @return FTP文件信息
     */
    private static FTPFileInfo parseFTPFileInfo(String url) {

        String protocol = url.substring(0, url.indexOf("://"));
        String s1 = url.substring(url.indexOf("://") + 3);
        String s2 = s1.substring(0, s1.indexOf('/'));
        String[] split = s2.split(":");
        String hostName;
        int port;
        if (split.length < 1) {
            hostName = s2;
            port = 21;
        } else if (split.length < 2) {
            hostName = split[0];
            port = 21;
        } else {
            hostName = split[0];
            port = Integer.parseInt(split[1]);
        }
        String directory = null;
        if (s1.indexOf('/') < s1.lastIndexOf('/')) {
            directory = s1.substring(s1.indexOf('/') + 1, s1.lastIndexOf('/'));
        }
        String fileName = s1.substring(s1.lastIndexOf('/') + 1);

        return new FTPFileInfo()
                .setProtocol(protocol)
                .setHostName(hostName)
                .setPort(port)
                .setDirectory(directory)
                .setFileName(fileName);
    }

    /**
     * FTP文件信息
     */
    private static class FTPFileInfo {

        /**
         * 协议
         */
        private String protocol;

        /**
         * 主机名
         */
        private String hostName;

        /**
         * 端口
         */
        private int port;

        /**
         * 文件所在目录
         */
        private String directory;

        /**
         * 文件名
         */
        private String fileName;

        public String getProtocol() {
            return protocol;
        }

        public FTPFileInfo setProtocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public String getHostName() {
            return hostName;
        }

        public FTPFileInfo setHostName(String hostName) {
            this.hostName = hostName;
            return this;
        }

        public int getPort() {
            return port;
        }

        public FTPFileInfo setPort(int port) {
            this.port = port;
            return this;
        }

        public String getDirectory() {
            return directory;
        }

        public FTPFileInfo setDirectory(String directory) {
            this.directory = directory;
            return this;
        }

        public String getFileName() {
            return fileName;
        }

        public FTPFileInfo setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }
    }
}
