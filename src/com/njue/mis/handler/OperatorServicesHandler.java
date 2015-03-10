/**
 * ����Ա����ӿ�
 */
package com.njue.mis.handler;

import java.util.Vector;

import com.njue.mis.model.Operator;

public interface OperatorServicesHandler
{
	/**
	 * ��ѯ�û����������Ƿ���ȷ
	 * @param username �û���
	 * @param password ����
	 * @return ��ѯ���
	 */
	boolean loginCheck(String username,String password);
	/**
	 * �����ݿ�������µĲ���Ա
	 * @param operator ��װ�õĲ���Ա
	 * @return ִ�н��
	 */
	boolean addOperator(Operator operator);
	/**
	 * ɾ������Ա��Ϣ
	 * @param username ��ɾ���Ĳ���Ա���� 
	 * @return ִ�н��
	 */
	boolean deleteOperator(String username);
	/**
	 * �޸�����
	 * @param username ���޸ĵ��û���
	 * @param password ������
	 * @return ִ�н��
	 */
	boolean modifyPassword(String username,String password);
	/**
	 * ��ȡ�û�Ȩ��
	 * @param username �û���
	 * @return ��ѯ���
	 */
	String getPower(String username);
	/**
	 * ��ȡ����Ա������
	 * @param username �û���
	 * @return ����
	 */
	String getPassword(String username);
	/**
     * �жϲ���Ա�Ƿ����
     * @param id ��ѯ�Ĳ���Ա�ǳ�
     * @return ��ѯ���
     */
    boolean isExited(String username);
    /**
     * ��ȡ�ض�ְȨ���û���
     * @param type ����
     * @return �������
     */
    Vector<Operator> getOperator(String type);
    /**
     * ��ȡ�û�����Ϣ
     * @param userName �û���
     * @return  �û���Ϣ
     */
    Operator getOperatorInfo(String userName);
}