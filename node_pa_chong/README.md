�����ļ���

/home/node_pa_chong�µ�����

��װnode

$ wget http://nodejs.org/dist/v0.8.16/node-v0.8.16.tar.gz
$ tar zxvf node-v0.8.16.tar.gz
$ ./configure
$ make && make install

��װ�ú��� ����̨�����룺
$ node -v
v0.8.16
����node

���Ȱ�װnģ�飺
        npm install -g n

    ����node.js�������ȶ���
        n stable
        n 0.10.26 //���߿���ѡ��������ָ���汾


    npm -v          #��ʾ�汾�����npm �Ƿ���ȷ��װ��
     
    npm install express   #��װexpressģ��
     
    npm install -g express  #ȫ�ְ�װexpressģ��
     
    npm list         #�г��Ѱ�װģ��
     
    npm show express     #��ʾģ������
     
    npm update        #������ǰĿ¼�µ���Ŀ������ģ��
     
    npm update express    #������ǰĿ¼�µ���Ŀ��ָ��ģ��
     
    npm update -g express  #����ȫ�ְ�װ��expressģ��
     
    npm uninstall express  #ɾ��ָ����ģ��
��ʱ����
node app.js

��װforever npm -g install forever

���� forever start app.js