�ð�������Authenticator��AuthenticationStrategy��֤����

Authenticator��ְ������֤�û�����shiro api�������֤���ĵ���ڵ�
Security�ӿڼ̳���Authenticator�ӿڣ�Authenticator�ӿ���һ��ʵ����ModularRealmAuthenticator
ModularRealmAuthenticator���û���֤����ί�и����Realm
��֤����ͨ��AuthenticationStrategy�ӿ�ָ����shiro�ṩ��3��Ĭ��ʵ����

1. FirstSuccessfulStrategy:ֻҪ��һ��Realm��֤�ɹ����ɣ�ֻ���ص�һ����֤�ɹ�����֤��Ϣ�������ĺ���
2. AtLeastOneSuccessfulStrategy:ֻҪ��һ��Realm��֤�ɹ����ɣ���������Realm�����֤�ɹ�����֤��Ϣ
3. AllSuccessfulStrategy:����Realm��֤�ɹ�����ɹ����ҷ�������Realm�����֤�ɹ�����֤��Ϣ�������һ��Realm��֤ʧ�ܾ�ʧ����!

ModularRealmAuthenticatorĬ��ʹ��AtLeastOneSuccessfulStrategy����