package code.person.pojo.account;

import java.util.List;

import code.person.pojo.interestRate.InterestRate;
import code.person.util.Code;


public class Account {
	public boolean hasSubAccount = false;
	public List<Account> subAccounts; 
	//���
	public int xh = 0;
	//
	public boolean lc = false;
	//�˿���
	public String zkh;
	//���ʺ�
	public String fzh;
	
	public String zhlx;
	//����
	public String lx;
	//����
	public String bz;
	//���
	public String ye;
	public String getYe() {
		return Code.formatAmount(ye, true);
	}
	//��Ʒ
	public String cp;
	//�������
	public String kyye;
	public String getkyye() {
		return Code.formatAmount(kyye, true);
	}
	public String djje;
	public String getDjje() {
		return Code.formatAmount(djje, true);
	}
	public String ll;
	public String khrq;
	public String getKhrq() {
		return Code.formatDate(khrq);
	}
	public String cq;
	public String dqrq;
	public String getDqrq() {
		return Code.formatDate(dqrq);
	}
	public String khwd;
	public String zhzt;
	public String zl;
	public String yqbj;
	public String getYqbj() {
		return Code.formatAmount(yqbj, true);
	}
	public String qx;
	public String getQx() {
		return Code.formatAmount(qx, true);
	}
	public String yhbj;
	public String getYhbj() {
		return Code.formatAmount(yhbj, true);
	}
	public String yhlx;
	public String getYhlx() {
		return Code.formatAmount(yhlx, true);
	}
	public String xyhkr;
	public String kyed;
	public String bqsyyhje;
	public String getBqsyyhje() {
		return Code.formatAmount(bqsyyhje, true);
	}
	public String dqqkje;
	public String getDqqkje() {
		return Code.formatAmount(dqqkje, true);
	}
	public String xyed;
	public String getXyed() {
		return Code.formatAmount(xyed, true);
	}
	public String qxed;
	public String getQxed() {
		return Code.formatAmount(qxed, true);
	}
	public String kyqxed;
	public String getKyqxed() {
		return Code.formatAmount(kyqxed, true);
	}
	public String bqyhke;
	public String getBqyhke() {
		return Code.formatAmount(bqyhke, true);
	}
	public String bqzdhke;
	public String getBqzdhke() {
		return Code.formatAmount(bqzdhke, true);
	}
	public String bqzdr;
	public String getBqzdr() {
		return Code.formatDate(bqzdr);
	}
	public String bqdqhkr;
	public String getBqdqhkr() {
		return Code.formatDate(bqdqhkr);
	}
	public String cpmc;
	public String rgje;
	public String getRgje() {
		return Code.formatAmount(rgje, true);
	}
	public String rgrq;
	public String getRgrq() {
		return Code.formatDate(rgrq);
	}
	public String yqll;
	public String yqsy;
	public String xgzh;
	public String mrzh;
	public String wxtz;

	public boolean getMrzh() {
		return "1".equalsIgnoreCase(mrzh);
	}
	public boolean getWxtz() {
		return "1".equalsIgnoreCase(wxtz);
	}
	public String getOzkh() {
		return Code.maskAccountNumber(zkh);
	}
	public String zt;
	
	//����� 
	//���ִ���
	public String cz;
	//��������
	public String czmc;
	//��ǰ����
	public String dqll;
	//���ʱ���
	public String llbs;
	//��������
	public List<InterestRate> llxq; 
}
