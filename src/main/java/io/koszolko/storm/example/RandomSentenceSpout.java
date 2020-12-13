package io.koszolko.storm.example;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomSentenceSpout extends BaseRichSpout {
    private static final Logger LOG = LoggerFactory.getLogger(RandomSentenceSpout.class);

    private SpoutOutputCollector collector;
    private Random rand;

    @Override
    public void open(Map<String, Object> conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
        rand = new Random();
    }

    @Override
    public void nextTuple() {
        Utils.sleep(100);
        String[] sentences = new String[]{
            sentence("the cow jumped over the moon"), sentence("an apple a day keeps the doctor away"),
            sentence("four score and seven years ago"), sentence("snow white and the seven dwarfs"), sentence("i am at two with nature")
        };
        final String sentence = sentences[rand.nextInt(sentences.length)];

        LOG.debug("Emitting tuple: {}", sentence);

        String msgID = UUID.randomUUID().toString();
        collector.emit(new Values(sentence), msgID);
    }

    private String sentence(String input) {
        return input;
    }

    @Override
    public void ack(Object id) {
    }

    @Override
    public void fail(Object id) {
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}