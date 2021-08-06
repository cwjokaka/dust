package com.dust.actor;

public interface MailBox<T> {

    /**
     * 接收邮件
     * @param message
     */
    void receive(Mail<T> message);

    /**
     * 获取邮箱队列里的邮件，如果目前没有邮件，则进行阻塞
     * @return 邮件
     */
    Mail<T> take() throws InterruptedException;

    /**
     * 清空邮箱
     */
    void clear();

}
