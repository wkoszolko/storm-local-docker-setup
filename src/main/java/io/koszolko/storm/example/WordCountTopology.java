package io.koszolko.storm.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.topology.ConfigurableTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * This topology demonstrates Storm's stream groupings and multilang
 * capabilities.
 */
@Slf4j
public class WordCountTopology extends ConfigurableTopology {
    public static void main(String[] args) {
        ConfigurableTopology.start(new WordCountTopology(), args);
    }

    @Override
    protected int run(String[] args) {

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new RandomSentenceSpout(), 2);
        builder.setBolt("split", new SplitBolt(), 4).shuffleGrouping("spout");
        builder.setBolt("count", new WordCountBolt(), 6).fieldsGrouping("split", new Fields("word"));

        conf.setDebug(true);
        conf.setStatsSampleRate(1.0D);
        conf.setNumWorkers(1);
        conf.setNumAckers(1);

        String topologyName = "word-count";
        if (args != null && args.length > 0) {
            topologyName = args[0];
        }
        log.info("Submit {} topology", topologyName);
        return submit(topologyName, conf, builder);
    }
}
