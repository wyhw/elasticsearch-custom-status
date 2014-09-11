Elasticsearch custom status
===========================

A elasticsearch plugin, Control elasticsearch custom status.

##Instalation
Run 

	cd elasticsearch

	wget http://github.com/wyhw/elasticsearch-custom-status/blob/master/elasticsearch-custom-status-0.0.2.zip?raw=true --no-check-certificate

	bin/plugin --url file://`pwd`/elasticsearch-custom-status-0.0.3.zip --install elasticsearch-custom-status

then Restart elasticsearch.

##Usage

####View status: 

	http://localhost:9200/_customstatus

####Set custom status

	http://localhost:9200/_customstatus?status=meetyou


##Version mapping

	master   =>    Elasticsearch 1.3.x

	0.0.3	 =>    Elasticsearch 1.3.x
	0.0.2    =>    Elasticsearch 1.2.x
	0.0.1    =>    Elasticsearch 1.0.x


