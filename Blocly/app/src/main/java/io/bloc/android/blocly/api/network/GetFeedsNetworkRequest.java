package io.bloc.android.blocly.api.network;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Kari on 11/4/2015.
 */
public class GetFeedsNetworkRequest extends NetworkRequest<List<GetFeedsNetworkRequest.RssFeed>> {

    public static final int ERROR_PARSING = 3;

    private static final String XML_TAG_TITLE = "title";
    private static final String XML_TAG_DESCRIPTION = "description";
    private static final String XML_TAG_LINK = "link";
    private static final String XML_TAG_ITEM = "item";
    private static final String XML_TAG_PUB_DATE = "pubDate";
    private static final String XML_TAG_GUID = "guid";
    private static final String XML_TAG_ENCLOSURE = "enclosure";
    private static final String XML_TAG_ATTRIBUTE_URL = "url";
    private static final String XML_TAG_ATTRIBUTE_TYPE = "type";

    String [] feedUrls;

    public GetFeedsNetworkRequest(String... feedUrls){
        this.feedUrls = feedUrls;
    }
    @Override
    public List<RssFeed> performRequest() {
        List<RssFeed> responseFeeds = new ArrayList<RssFeed>(feedUrls.length);

        for (String feedUrlString : feedUrls){
            InputStream inputStream = openStream(feedUrlString);
            if (inputStream == null) {
                return null;
            }
            try {
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document xmlDocument = documentBuilder.parse(inputStream);
                String channelTitle = optFirstTagFromDocument(xmlDocument, XML_TAG_TITLE);
                String channelDescription = optFirstTagFromDocument(xmlDocument, XML_TAG_DESCRIPTION);
                String channelURL = optFirstTagFromDocument(xmlDocument, XML_TAG_LINK);

                NodeList allItemNodes = xmlDocument.getElementsByTagName(XML_TAG_ITEM);
                List<RssItem> responseItems = new ArrayList<RssItem>(allItemNodes.getLength());
                for (int itemIndex = 0; itemIndex < allItemNodes.getLength(); itemIndex++){
                    String itemURL = null;
                    String itemTitle = null;
                    String itemDescription = null;
                    String itemGUID = null;
                    String itemPubDate = null;
                    String itemEnclosureURL = null;
                    String itemEnclosureMIMEType = null;

                    Node itemNode = allItemNodes.item(itemIndex);
                    NodeList tagNodes = itemNode.getChildNodes();
                    for (int tagIndex=0; tagIndex < tagNodes.getLength(); tagIndex++){
                        Node tagNode = tagNodes.item(tagIndex);
                        String tag = tagNode.getNodeName();
                        if (XML_TAG_LINK.equalsIgnoreCase(tag)){
                            itemURL = tagNode.getTextContent();
                        } else if (XML_TAG_TITLE.equalsIgnoreCase(tag)){
                            itemTitle = tagNode.getTextContent();
                        } else if (XML_TAG_DESCRIPTION.equalsIgnoreCase(tag)) {
                            itemDescription = tagNode.getTextContent();
                        } else if (XML_TAG_ENCLOSURE.equalsIgnoreCase(tag)){
                            NamedNodeMap enclosureAttributes = tagNode.getAttributes();
                            itemEnclosureURL = enclosureAttributes.getNamedItem(XML_TAG_ATTRIBUTE_URL).getTextContent();
                            itemEnclosureMIMEType = enclosureAttributes.getNamedItem(XML_TAG_ATTRIBUTE_TYPE).getTextContent();
                        } else if (XML_TAG_PUB_DATE.equalsIgnoreCase(tag)) {
                            itemPubDate = tagNode.getTextContent();
                        } else if (XML_TAG_GUID.equalsIgnoreCase(tag)) {
                            itemGUID = tagNode.getTextContent();
                        }
                    }
                    responseItems.add(new RssItem(itemURL, itemTitle, itemDescription,
                            itemGUID, itemPubDate, itemEnclosureURL, itemEnclosureMIMEType));
                }
                responseFeeds.add(new RssFeed(feedUrlString, channelTitle, channelURL, channelDescription, responseItems));

            } catch (IOException e) {
                e.printStackTrace();
                setErrorCode(ERROR_IO);
                return null;
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
                setErrorCode(ERROR_PARSING);
                return null;
            } catch (SAXException e) {
                e.printStackTrace();
                setErrorCode(ERROR_PARSING);
                return null;
            }
        }
        return responseFeeds;
    }

    private String optFirstTagFromDocument(Document document, String tagName){
        NodeList elementsByTagName = document.getElementsByTagName(tagName);
        if (elementsByTagName.getLength() >0){
            return elementsByTagName.item(0).getTextContent();
        }
        return null;
    }

    public static class RssFeed {
        public final String channelFeedURL;


        public final String channelTitle;
        public final String channelURL;
        public final String channelDescription;
        public final List<RssItem> channelItems;

        RssFeed(String channelFeedURL, String channelTitle, String channelURL,
                     String channelDescription, List<RssItem> channelItems) {
            this.channelDescription = channelDescription;
            this.channelFeedURL = channelFeedURL;
            this.channelTitle = channelTitle;
            this.channelURL = channelURL;
            this.channelItems = channelItems;
        }

        public String getDescription() {
            return channelDescription;
        }

        public String getChannelFeedURL() {
            return channelFeedURL;
        }

        public List<RssItem> getChannelItems() {
            return channelItems;
        }

        public String getTitle() {
            return channelTitle;
        }

        public String getChannelURL() {
            return channelURL;
        }
    }

    public static  class RssItem {
        public final String itemURL;
        public final String itemTitle;
        public final String itemDescription;
        public final String itemGUID;
        public final String itemPubDate;
        public final String itemEnclosureURL;

        public RssItem(String itemURL, String itemTitle,String itemDescription,
                            String itemGUID, String itemPubDate, String itemEnclosureURL,
                            String itemEnclosureMIMEType) {
            this.itemDescription = itemDescription;
            this.itemURL = itemURL;
            this.itemTitle = itemTitle;
            this.itemGUID = itemGUID;
            this.itemPubDate = itemPubDate;
            this.itemEnclosureURL = itemEnclosureURL;
            this.itemEnclosureMIMEType = itemEnclosureMIMEType;
        }

        public final String itemEnclosureMIMEType;

        public String getDescription() {
            return itemDescription;
        }

        public String getItemEnclosureMIMEType() {
            return itemEnclosureMIMEType;
        }

        public String getImageUrl() {
            return itemEnclosureURL;
        }

        public String getItemGUID() {
            return itemGUID;
        }

        public String getItemPubDate() {
            return itemPubDate;
        }

        public String getTitle() {
            return itemTitle;
        }

        public String getUrl() {
            return itemURL;
        }
    }
}
