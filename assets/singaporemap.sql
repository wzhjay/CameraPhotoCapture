-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 06, 2014 at 02:17 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `singaporemap`
--
CREATE DATABASE IF NOT EXISTS `singaporemap` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `singaporemap`;

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE IF NOT EXISTS `contact` (
  `temple_id` int(11) NOT NULL,
  `district` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL COMMENT 'address',
  `pos_code` varchar(6) DEFAULT NULL COMMENT 'postal code',
  `phone_no` int(8) DEFAULT NULL COMMENT 'phone number',
  `fax_no` int(8) DEFAULT NULL COMMENT 'fax number',
  PRIMARY KEY (`temple_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`temple_id`, `district`, `address`, `pos_code`, `phone_no`, `fax_no`) VALUES
(1, 'TANJONG PAGAR GRC', '15 Magazine Rd', '059568', 65332880, 65367315),
(2, 'JURONG GRC ', '553 Bukit Batok Street 23', '659521', 65617992, 0),
(3, 'NEE SOON GRC ', '362, Yishun Avenue 3', '769060', 67525282, 0),
(4, 'SEMBAWANG GRC', '31 Marsiling Industrial Estate Road 3', '739255', 63682301, 0),
(5, 'ANG MO KIO GRC', '791 Ang Mo Kio Ave 1', '569974', 0, 0),
(6, 'ANG MO KIO GRC ', '46 Ang Mo Kio Street 61', '569161', 0, 0),
(7, 'ANG MO KIO GRC ', '48 Ang Mo Kio Street 61', '569162', 64577757, 0),
(8, 'RADIN MAS SMC ', '98T Redhill Close', '158900', 0, 0),
(9, 'ALJUNIED GRC ', '7 Tampines Avenue', '529786', 62996588, 62995755),
(10, 'JURONG GRC', '397 Bt Batok West Ave 8', '658964', 0, 0),
(11, 'MARINE PARADE GRC ', '37 Kim Chuan Drive', '537089', 67446969, 63823969),
(12, 'JURONG GRC ', '10 Bukit Batok Street 21', '659632', 65677382, 0),
(13, '', '55 Jalan Plunton', '', 0, 0),
(14, 'MOULMEIN-KALLANG GRC', '432 Geylang Lor 3', '388903', 68464310, 0),
(15, 'CHUA CHU KANG GRC', '33 Kranji Loop', '739565', 62693315, 0),
(16, 'ANG MO KIO GRC ', '46 Ang Mo Kio Street 61', '569161', 0, 0),
(17, 'ANG MO KIO GRC ', 'Serangoon North Avenue 3', '555862', 0, 0),
(18, 'ALJUNIED GRC ', '800 Tampines Road', '518476', 0, 0),
(19, 'NEE SOON GRC ', '525 Yishun Industrial Park A', '768769', 67526752, 0);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `temple_id` int(11) NOT NULL,
  `lat` float(10,6) DEFAULT NULL COMMENT 'x-coord',
  `lon` float(10,6) DEFAULT NULL COMMENT 'y-coord',
  `relo_yr` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT 'last relocated year',
  `prev_lat` float(10,6) DEFAULT NULL COMMENT 'x-coord',
  `prev_lon` float(10,6) DEFAULT NULL COMMENT 'y-coord',
  `relo_lat` float(10,6) DEFAULT NULL COMMENT 'x-coord',
  `relo_lon` float(10,6) DEFAULT NULL COMMENT 'y-coord',
  PRIMARY KEY (`temple_id`,`relo_yr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`temple_id`, `lat`, `lon`, `relo_yr`, `prev_lat`, `prev_lon`, `relo_lat`, `relo_lon`) VALUES
(1, 1.289392, 103.842674, '1876', 0.000000, 0.000000, 0.000000, 0.000000),
(2, 1.339946, 103.757187, '', 0.000000, 0.000000, 0.000000, 0.000000),
(3, 1.422711, 103.833008, '', 0.000000, 0.000000, 0.000000, 0.000000),
(4, 1.439625, 103.782616, '1945s', 0.000000, 0.000000, 0.000000, 0.000000),
(5, 1.366330, 103.838722, '', 0.000000, 0.000000, 0.000000, 0.000000),
(6, 1.380598, 103.841438, '', 0.000000, 0.000000, 0.000000, 0.000000),
(7, 1.380760, 103.841377, '1918', 0.000000, 0.000000, 0.000000, 0.000000),
(8, 1.286395, 103.816315, '', 0.000000, 0.000000, 0.000000, 0.000000),
(9, 1.366981, 103.927673, '1928', 0.000000, 0.000000, 0.000000, 0.000000),
(10, 1.347130, 103.740059, '', 0.000000, 0.000000, 0.000000, 0.000000),
(11, 1.341083, 103.890938, '1950', 0.000000, 0.000000, 0.000000, 0.000000),
(12, 1.346096, 103.751152, '1900', 0.000000, 0.000000, 0.000000, 0.000000),
(13, 1.317344, 103.873779, '', 0.000000, 0.000000, 0.000000, 0.000000),
(14, 1.317344, 103.873779, '1950', 0.000000, 0.000000, 0.000000, 0.000000),
(15, 1.436064, 103.753288, '1930', 0.000000, 0.000000, 0.000000, 0.000000),
(16, 1.380598, 103.841438, '', 0.000000, 0.000000, 0.000000, 0.000000),
(17, 1.373129, 103.870934, '', 0.000000, 0.000000, 0.000000, 0.000000),
(18, 1.368357, 103.924660, '', 0.000000, 0.000000, 0.000000, 0.000000),
(19, 1.443550, 103.835419, '1930', 0.000000, 0.000000, 0.000000, 0.000000);

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE IF NOT EXISTS `members` (
  `user_id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(65) NOT NULL DEFAULT '',
  `password` varchar(65) NOT NULL DEFAULT '',
  `priority` int(1) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`user_id`, `username`, `password`, `priority`) VALUES
(1, 'admin', 'password', 1);

-- --------------------------------------------------------

--
-- Table structure for table `origin`
--

CREATE TABLE IF NOT EXISTS `origin` (
  `temple_id` int(11) NOT NULL,
  `mother_tem` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `spec_line` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `linkage` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `Return visits/reconstruction/regular funding/other` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`temple_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `origin`
--

INSERT INTO `origin` (`temple_id`, `mother_tem`, `spec_line`, `linkage`, `Return visits/reconstruction/regular funding/other`) VALUES
(1, '中国漳州威惠庙', '陈氏宗祠', '', NULL),
(2, '中国江西省赣州府寻邬县', '中国江西省赣州府寻邬县', '', '曾组团回去祖庙进香'),
(3, '中国福建省泉州市百源川福庭村长秀馆', '福建省泉州市百源川福庭村', '波德申云南古庙（总庙）卓氏公会（东南亚多地）', '1990 捐资建福庭会宾馆，新币76,950元，港币25,300元，人民币5,4001993 捐资福庭小学暨祖祠重建，新币181,461元，人民币25,600元1996 捐资福庭小学增建课室，新币 30,200 元 1997 捐资重建泉州百源川祖祠，新币 26,200 元 1998 翔云镇福庭村水泥路，新币90,000元'),
(4, '中国福建省安溪县尚卿乡后塘村“义星堂”', '福建省安溪县尚卿乡后塘村', '凤图庙自成祖庙，分炉出以下二庙，后再统一   1、兀兰万国芭之环图庙   2、三巴旺松利园之保安庙\n同时也摄取其他庙宇的神明于一庙：    1、兀兰路惹兰国民的花果山平安明德宫   2、义星堂   3、观音庙', NULL),
(5, '中国福建泉州南安县玉湖村檺林庙', '中国福建泉州南安县玉湖村檺林庙', '', NULL),
(6, '', '福建南安英都乡\n葛岸馆在西', '', NULL),
(7, '分炉自 中国安溪山美村华堂府', '安溪山美村华堂府', '', NULL),
(8, '福建同安新玗金丙村炎帝庙仙祖宫', '福建同安新玗金丙村', '', '进香：一、福建同安新玗金丙村炎帝庙 （仙祖宫）             二、湖南株洲炎帝陵             三、台湾新竹东宁馆             四、台湾苗栗五谷宫筹钱兴建中国福建省安溪县龙门镇美卿乡圣齐庙（朱荣池三府大人公庙）'),
(9, '华汤府：福建安溪县榜头市\n灵应宫：福建安溪县榜头山平尾垵 圣济庙：福建安溪县榜头坑内', '福建安溪县榜头市白姓族人', '', '筹钱兴建中国福建省安溪县龙门镇美卿乡圣齐庙（朱荣池三府大人公庙）'),
(10, '', '感应尊王、麻府相公：爱民宫（黄姓祖佛）', '', NULL),
(11, '', '福建莆田江口西刘村', '', NULL),
(12, '中国福建安溪湖头下坑村', '吴公真仙由黄姓家族供奉', '', '主席黄宝华等人经常回返祖庙进香'),
(13, '', '福建安溪林姓族人', '', NULL),
(14, '中国福建南安诗山鹏峰村之蓬峰庙', '中国福建南安诗山鹏峰村', '1. 每年保生大帝诞辰，鹏峰庙根据传统习俗     ，举行“割火”仪式，理事和善信抬保生大     帝金身的木桥，在老台鼓队的吹奏声中，     到水廊头凤山寺进香，向广泽尊王取香火\n2. 2008年正月初四，联合祖庙进香取圣火，     恭迎保生大帝直诣凤山寺古刹，拜谒广泽     尊王请取香火。回返新加坡后，在保生大     帝诞辰，再到水廊头凤山寺进香，再度向     广泽尊王多取一次香火。', '1. 1950s, 中国多庙被毁，南安诗山鹏峰村之     蓬峰庙信徒在危急中，以三个木像换回     保生大帝、清水祖师、哪吒元帅三尊金身     ，托乡亲带来新加坡保管和供奉。2. 2004，返乡谒祖3. 2008，返乡谒祖'),
(15, '中国福建省泉州府安溪县湖头里七寨庙', '福建省泉州府安溪县湖头里', '', NULL),
(16, '', '福建南安英都乡水沟馆在东，洪姓家族', '', NULL),
(17, '', '谢氏宗祠（另有吴姓、王姓族人）', '', NULL),
(18, '', '许氏家族（许氏总会）', '', NULL),
(19, '中国福清市江兜村昭灵祖庙（分香）后殿为江兜王氏宗祠，隶属庐山教', '福清市江兜村昭灵祖庙（王姓族人）', '', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `temple`
--

CREATE TABLE IF NOT EXISTS `temple` (
  `temple_id` int(11) NOT NULL AUTO_INCREMENT,
  `chi_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Chinese Name (',
  `hypy_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Hanyu Pinyin (',
  `off_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Official Name',
  `cat` int(2) DEFAULT NULL COMMENT 'Code of Temple Category',
  `const_yr` int(6) DEFAULT NULL COMMENT 'constructed in ',
  `reno_yr` int(6) DEFAULT NULL COMMENT 'last renovated year',
  `god` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'God of Worship ',
  `dialect` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Dialect',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `img_name` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`temple_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `temple`
--

INSERT INTO `temple` (`temple_id`, `chi_name`, `hypy_name`, `off_name`, `cat`, `const_yr`, `reno_yr`, `god`, `dialect`, `comment`, `img_name`) VALUES
(1, '保赤宫（陈氏宗祠）', 'Bao Chi Gong', 'Po Chiak Keng (Tan Si Chong Su Temple)', 1, 1876, 0, '开漳圣王陈元光', '福建', '', ''),
(2, '本元虚灵山总道堂', 'Ben Yuan Xu Ling Shan Zong Dao Tang', 'Poon Guan Hui Leng Sun Chong Tao Tong', 10, 0, 0, '真空教主', '福建', '', ''),
(3, '长秀馆', 'Chang Xiu Guang', 'Chang Xiu Guang ( Teong Siew Kuan Temple)', 1, 0, 0, '王爷：卓府三位大人', '福建南安', '', ''),
(4, '风图庙', 'Feng Tu Miao ', 'Hong Tho Bilw Temple', 1, 1876, 0, '关帝（协天上帝）', '福建泉州安溪', '', ''),
(5, '檺林宫', 'Gao Lin Gong', 'Kong Lim Kong Temple', 1, 1876, 0, '王爷：朱、邢、李三王府大人，杨府元帅', '福建', '', ''),
(6, '葛岸馆', 'Ge An Guan', 'Shui Gou Guan Ge An Guan', 0, 1876, 0, '', '', '', ''),
(7, '华堂府', 'Hua Tang Fu', 'Hua Tang Fu ', 1, 1876, 0, '王爷：施府王爷', '福建', '', ''),
(8, '介谷殿', 'Jie Gu Dian', 'Jie Gu Dian', 1, 1876, 0, '五谷仙帝（神农炎帝）', '福建同安新玗金丙村', '', ''),
(9, '九玄宫 aka 玄女妈宫', 'Jiu Xian Gong aka Xuan Nv Ma Gong', 'Jiu Xian Gong aka Xuan Nv Ma Gong', 1, 1876, 0, '九天玄女', '福建安溪', '', ''),
(10, '临江殿 / 对山庙 / 爱民宫', 'Lin Jiang Dian / Dui Shan Miao / Ai Min Gong', 'Lin Jiang Dian / Dui Shan Miao / Ai Min Gong', 1, 1876, 0, '王爷：朱、邢、李三府大人（临江殿）苏妈夫人：对山庙感应尊王、麻府相公：爱民宫（黄姓祖佛）', '福建安溪', '', ''),
(11, '凌霄殿', 'Lin Xiao Dian', 'Ling Xiao Dian', 1, 1876, 0, '玉皇大帝、温公元帅', '福建莆田江口西刘村', '', ''),
(12, '龙凤洞', 'Long Feng Dong', 'Ling Hong Tong Temple', 1, 1876, 0, '吴公真仙（保生大帝）', '福建安溪', '', ''),
(13, '龙山宫', 'Long Shan Gong', 'Long Shan Gong', 0, 1876, 0, '', '', '', ''),
(14, '鹏峰宫', 'Peng Feng Gong', 'Peng Hong Temple', 1, 1876, 0, '保生大帝、清水祖师', '福建', '', ''),
(15, '七寨廟', 'Qi Zhai Miao', 'Chek Chai Temple', 1, 1876, 0, '山西父子关公', '福建南安/安溪', '', ''),
(16, '水沟馆', 'Shui Gou Guan Ge An Guan', 'Shui Gou Guan Ge An Guan', 0, 1876, 0, '', '', '', ''),
(17, '午峰岩', 'Wu Feng Yan', 'Wu Feng Yan', 1, 1876, 0, '', '', '', ''),
(18, '协天宫', 'Xie Tian Gong ', 'Hiap Tien Keng Leng Poh Tien Tp', 1, 1876, 0, '关帝、许氏真君', '福建安溪', '', ''),
(19, '昭灵庙（莆田江兜）', 'Zhao Lin Miao', 'Cheow Leng Beo (Xin Hua Temple)', 1, 1876, 0, '柳金圣侯（千里眼柳圣君、顺风耳金圣君合一）宣赞元帅、达地圣侯', '福建福清新厝江兜村', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `worships`
--

CREATE TABLE IF NOT EXISTS `worships` (
  `temple_id` int(11) NOT NULL,
  `secondary god` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `worship_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `Dates of other rites` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `Xi Tai to Honour God(s)` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `spirit_med` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `member` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`temple_id`,`secondary god`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `worships`
--

INSERT INTO `worships` (`temple_id`, `secondary god`, `worship_date`, `Dates of other rites`, `Xi Tai to Honour God(s)`, `spirit_med`, `member`) VALUES
(1, '陈姓祖宗虞舜重华公;魏妈;观音;妈祖;孔子;玉皇大帝', '正月初九;二月十五;', '除夕   迎财神正月初一至三十日 拜太岁正月十五      元宵节七月十五      中元节十一月初三 冬至祭祖', '', '', '0'),
(2, '真空教历代祖师和先生', '四月初八;四月初九', '', '四月初八、初九举行大小”放花“仪式在诵经时当空杀牲杀猪羊放猪羊血，叫放大花杀鸡鸭放鸡鸭血，叫放小花以沙牲代人消灾，叫度人兼度物谓被杀物可以代劫之功转为人道', '没有', '不详'),
(3, '五营将军;诸神', '十月初六;十月初七', '', '十月初六、初七 卓府三位大人千秋福建歌仔酬神戏连续两天', '无，由本地福建籍道士举行法事', '300s（卓姓会员）'),
(4, '周仓;关平;值年太岁;五营将军;观世音菩萨;齐天大圣;释迦牟尼佛;地藏王菩萨;大二爷伯;大伯公;虎爷;黄飞鸿祖师', '五月十三日', '', '五月十三   协天大帝千秋歌仔戏', '乩童扶乩，关帝公救世', '300s'),
(5, '大使;二帅;三帅;四帅;五帅;七帅', NULL, '', '', '', '0'),
(6, '', NULL, '', '', '', '0'),
(7, '', '十月初六;十月初七 ', '', '', '', '0'),
(8, '', '二月十四;二月十五', '', '', '', '0'),
(9, '田都元帅;天师大帝;如来佛祖;朱荣池三府大人;齐天大圣;善财童子', '正月十五;正月十六;四月十五;四月十六;六月廿四;十月十五;十月十六', '', '闽剧酬神正月十五、六   田都元帅千秋四月十五、六   九天玄女真仙千秋十月十五、六   朱荣池大人千秋', '九天玄女救世', '0'),
(10, '', NULL, '', '', '', '0'),
(11, '', NULL, '', '', '', '0'),
(12, '玉皇上帝;大伯公;观音佛祖;张公圣君;五谷真仙;黄老仙师;玄天上帝', '正月初八;三月十二;六月廿三;十月初九', '', '正月初一至十五 清中国福建安溪道士 正月初八  玉皇上帝诞辰  闽剧三月十二   大伯公千秋   闽剧\n六月廿三   众神诞辰   闽剧十月初九   吴公真仙诞辰   闽剧', '', '0'),
(13, '', NULL, '', '', '', '0'),
(14, '哪吒元帅;枝公', '三月', '', '', '', '0'),
(15, '周仓;关平;荣府二王;广泽尊王;城隍伯主;大二爷伯;地藏菩萨', '正月初九;正月廿九;正月三十;六月初一;八月廿二;八月廿三;十月廿一;十月廿二', '正月十五、十六   庆元宵十一月十三   答谢神恩（谢港）', '', '', '0'),
(16, '', NULL, '', '', '', '0'),
(17, '', NULL, '', '', '', '0'),
(18, '', NULL, '', '', '', '0'),
(19, '尊主明王;后土夫人;法主仙妃;金韩二将;三殿真君;白马元帅;柳金圣侯', '正月初九;三月初九;五月十三 ;十月十一', '农历正月初四  迎神农历正月十四  庆元宵农历九月十九  重阳祭祖（王氏宗祠）', '五月十三  达地王郎君千秋演戏', '', '0');

CREATE TABLE IF NOT EXISTS `image`(
  `temple_id` int(11) NOT NULL,
  `img_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`temple_id`, `img_name`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `image` (`temple_id`, `img_name`) VALUES
(1,'1333888659DSCF5268.jpg'),
(3,'1333888679DSCF5270.jpg'),
(3,'1333888692DSCF5274.jpg');
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
