/**
 * arcgis控件
 * @param geometry
 * @returns
 */

//地名标注
function showLabel(featureLayer,labelStr) {
    require([ "esri/map", "dojo/_base/Color", "esri/symbols/Font", "esri/symbols/TextSymbol", "esri/geometry/Point", "esri/layers/LabelClass",
        "dojo/domReady!" ], function(Map, Color, Font, TextSymbol, Point, LabelClass) {
        var labelSymbol = new TextSymbol().setColor(new Color("#000000"));
        labelSymbol.font.setSize("10pt");
        labelSymbol.font.setFamily("新宋体");
        var json = {
            "labelExpressionInfo" : {
                "value" : "{"+labelStr+"}"
            },
            "useCodedValues" : false,
            "labelPlacement" : "center-right"
        };
        var labelClass = new LabelClass(json);
        labelClass.symbol = labelSymbol;
        featureLayer.setLabelingInfo([ labelClass ]);
    })

}

function basemapGallery(map){
    require([
        "esri/map", "esri/dijit/BasemapGallery", "esri/arcgis/utils",
        "dojo/parser",
        "dijit/layout/BorderContainer", "dijit/layout/ContentPane", "dijit/TitlePane",
        "dojo/domReady!"
    ], function(
        Map, BasemapGallery, arcgisUtils,
        parser
    ) {
        parser.parse();

        //add the basemap gallery, in this case we'll display maps from ArcGIS.com including bing maps
        var basemapGallery = new BasemapGallery({
            showArcGISBasemaps: true,
            map: map
        }, "basemapGallery");
        basemapGallery.startup();

        basemapGallery.on("error", function(msg) {
            console.log("basemap gallery error:  ", msg);
        });
    });
}

function measureTool(map){
    require([
        "dojo/dom",
        "esri/Color",
        "dojo/keys",
        "dojo/parser",
        "esri/config",
        "esri/sniff",
        "esri/map",
        "esri/SnappingManager",
        "esri/dijit/Measurement",
        "esri/layers/FeatureLayer",
        "esri/renderers/SimpleRenderer",
        "esri/tasks/GeometryService",
        "esri/symbols/SimpleLineSymbol",
        "esri/symbols/SimpleFillSymbol",
        "esri/dijit/Scalebar",
        "dijit/layout/BorderContainer",
        "dijit/layout/ContentPane",
        "dijit/TitlePane",
        "dijit/form/CheckBox",
        "dojo/domReady!"
    ], function(
        dom, Color, keys, parser,
        esriConfig, has, Map, SnappingManager, Measurement, FeatureLayer, SimpleRenderer, GeometryService, SimpleLineSymbol, SimpleFillSymbol
    ) {
        parser.parse();
        //This sample may require a proxy page to handle communications with the ArcGIS Server services. You will need to
        //replace the url below with the location of a proxy on your machine. See the 'Using the proxy page' help topic
        //for details on setting up a proxy page.
        esriConfig.defaults.io.proxyUrl = "localhost/DotNet/proxy.ashx";
        esriConfig.defaults.io.alwaysUseProxy = false;

        var sfs = new SimpleFillSymbol(
            "solid",
            new SimpleLineSymbol("solid", new Color([195, 176, 23]), 2),
            null
        );

        var measurement = new Measurement({
            map: map
        }, dom.byId("measurementDiv"));
        measurement.startup();
    });
}

function search(map){
    require([

        "esri/map",
        "esri/dijit/Search",
        "dojo/domReady!"

    ], function (Map, Search) {

        var search = new Search({
            map: map
        }, "search");
        search.startup();

    });
}

function scalebar(map){
    require([
        "esri/map", "esri/dijit/Scalebar",
        "dojo/parser",
        "dijit/layout/BorderContainer", "dijit/layout/ContentPane", "dojo/domReady!"
    ], function(
        Map, Scalebar,
        parser
    ) {
        parser.parse();

        var scalebar = new Scalebar({
            map: map,
            // "dual" displays both miles and kilometers
            // "english" is the default, which displays miles
            // use "metric" for kilometers
            scalebarUnit: "dual",
            attachTo:"bottom-left"
        });
    });

}

function searchFeature(map,featureLayer){
    require([
        "esri/map",
        "esri/layers/FeatureLayer",
        "esri/dijit/Search",
        "esri/InfoTemplate",
        "dojo/domReady!"
    ], function (Map, FeatureLayer, Search, InfoTemplate) {

        //Create search widget
        var search = new Search({
            map: map,
            //passing in empty source array to clear defaults such as
            //"All" and the ArcGIS Online World Geocoding service
            sources: [],
            zoomScale: 50000
        }, "searchFeature");

        //listen for the load event and set the source properties
        search.on("load", function () {

            var sources = search.sources;
            sources.push({
                featureLayer: featureLayer,
                placeholder: "搜索灾害体",
                enableLabel: false,
                searchFields: ["灾害体名称"],
                displayField: "灾害体名称",
                exactMatch: false,
                outFields: ["*"]

                //Create an InfoTemplate and include three fields
                //    infoTemplate: new InfoTemplate("信息", "</br>灾害体名称: ${JFBJ01A020}</br>")

            });
            //Set the sources above to the search widget
            search.set("sources", sources);
        });
        search.startup();
    })
}

//测量面积
function GetArea(geometry) {
    var geo = esri.geometry.webMercatorToGeographic(geometry);
    var Area = esri.geometry.geodesicAreas([ geometry ], esri.tasks.GeometryService.UNIT_SQUARE_METERS);
    var truearea = Area[0];
    console.log("面积：" + truearea + "平方米")
}
//初始范围
function homeButton(map) {
    require([ "esri/map", "esri/dijit/HomeButton", "dojo/domReady!" ], function(Map, HomeButton) {
        var home = new HomeButton({
            map : map
        }, "HomeButton");
        home.startup();

    });
}
//切换地图（联网）
function basemapGallery(map) {
    require([ "esri/map", "esri/dijit/BasemapGallery", "esri/arcgis/utils", "dojo/parser", "dijit/layout/BorderContainer",
        "dijit/layout/ContentPane", "dijit/TitlePane", "dojo/domReady!" ], function(Map, BasemapGallery, arcgisUtils, parser) {
        parser.parse();
        //add the basemap gallery, in this case we'll display maps from ArcGIS.com including bing maps
        var basemapGallery = new BasemapGallery({
            showArcGISBasemaps : true,
            map : map
        }, "basemapGallery");
        basemapGallery.startup();

        basemapGallery.on("error", function(msg) {
            console.log("basemap gallery error:  ", msg);
        });
    });
}
//切换底图（联网）
function basemapToggle(map) {
    require([ "esri/map", "esri/dijit/BasemapToggle", "dojo/domReady!" ], function(Map, BasemapToggle) {
        var toggle = new BasemapToggle({
            map : map,
            basemap : "satellite"
        }, "BasemapToggle");
        toggle.startup();

    });
}

//唯一值渲染
function renderer(featureLayer,rankStr) {
    require([ "esri/symbols/SimpleMarkerSymbol", "esri/symbols/SimpleLineSymbol", "esri/symbols/SimpleFillSymbol",
            "esri/renderers/ClassBreaksRenderer", "esri/renderers/UniqueValueRenderer", "dojo/_base/Color", "dojo/domReady!" ],
        function(SimpleMarkerSymbol,SimpleLineSymbol,SimpleFillSymbol,ClassBreaksRenderer,UniqueValueRenderer,Color) {
            var redSymbol, yellowSymbol, orangeSymbol, blueSymbol, greenSymbol;
            redSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.STYLE_CIRCLE, 15, new SimpleLineSymbol(SimpleLineSymbol.STYLE_CIRCLE, new Color([ 255, 0, 0 ]), 0), new Color([ 255, 0, 0 ]));
            yellowSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.STYLE_CIRCLE, 15, new SimpleLineSymbol(SimpleLineSymbol.STYLE_CIRCLE, new Color([ 255, 255, 0 ]), 0), new Color([255, 255, 0 ]));
            orangeSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.STYLE_CIRCLE, 15, new SimpleLineSymbol(SimpleLineSymbol.STYLE_CIRCLE, new Color([ 255, 128, 10 ]), 0), new Color([255, 128, 10 ]));
            blueSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.STYLE_CIRCLE, 15, new SimpleLineSymbol(SimpleLineSymbol.STYLE_CIRCLE, new Color([ 0, 0, 255 ]), 0), new Color([ 0, 0, 255 ]));
            greenSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.STYLE_CIRCLE, 15, new SimpleLineSymbol(SimpleLineSymbol.STYLE_CIRCLE, new Color([ 0, 255, 0 ]), 0), new Color([ 0, 255, 0 ])); // 唯一值渲染
            var rendererPoint = new UniqueValueRenderer(new SimpleMarkerSymbol(), rankStr);

            var visualVariables = [{
                "type": "sizeInfo",
                "field": "FBAD01A060",
                "valueUnit": "1",
                "minSize": 10,
                "maxSize": 25,
                "minDataValue": 0,
                "maxDataValue": 1000
            }];

            rendererPoint.addValue({value : 1, symbol : redSymbol, label : "警报级"});
            rendererPoint.addValue({value : 2, symbol : orangeSymbol, label : "警戒级"});
            rendererPoint.addValue({value : 3, symbol : yellowSymbol, label : "警示级"});
            rendererPoint.addValue({value : 4, symbol : blueSymbol, label : "注意级"});
            rendererPoint.addValue({value : 0, symbol : blueSymbol, label : "注意级"});

            rendererPoint.setVisualVariables(visualVariables);
            featureLayer.setRenderer(rendererPoint);

        })
}
/**
 * 信息弹窗
 */
function showTemplate(map, featureLayer) {
    require(
        [ "esri/map", "esri/InfoTemplate", "esri/dijit/InfoWindowLite","dojo/dom-construct","dojo/domReady!" ],
        function(Map, InfoTemplate,InfoWindowLite,domConstruct) {
            var infoWindow = new InfoWindowLite(null, domConstruct.create("div", null, null, map.root));
            infoWindow.startup();
            map.setInfoWindow(infoWindow);
            var infoTemplate = new InfoTemplate();
            var json="<b>名称: </b>${FBAD01A020}<br/>"
                +"<b>威胁人口：</b>${FBAD01A060}<br/>";
            +"<b>威胁财产：</b>${FBAD01A070}<br/>";
            map.infoWindow.resize(170, 120);
            infoTemplate.setContent(json);
            featureLayer.on("mouse-over",function(evt) {
                evt.graphic.setInfoTemplate(infoTemplate);
                var content = evt.graphic.getContent();
                map.infoWindow.setContent(content);
                map.infoWindow.show(evt.screenPoint,map.getInfoWindowAnchor(evt.screenPoint));
            });
        })


}
