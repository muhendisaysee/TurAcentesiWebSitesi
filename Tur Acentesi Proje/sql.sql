-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                10.3.13-MariaDB - mariadb.org binary distribution
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- tur_acentes için veritabanı yapısı dökülüyor
CREATE DATABASE IF NOT EXISTS `tur_acentes` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tur_acentes`;

-- tablo yapısı dökülüyor tur_acentes.acente
CREATE TABLE IF NOT EXISTS `acente` (
  `acente_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `acente_adi` varchar(30) DEFAULT 'Turkuaz',
  `sube_adi` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`acente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- tur_acentes.acente: ~10 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `acente` DISABLE KEYS */;
INSERT INTO `acente` (`acente_id`, `acente_adi`, `sube_adi`) VALUES
	(1, 'Turkuaz', 'Bahar'),
	(2, 'Turkuaz', 'Güneş'),
	(3, 'Turkuaz', 'Mevsim'),
	(4, 'Turkuaz', 'Işık'),
	(5, 'Turkuaz', 'Mavi'),
	(6, 'Turkuaz', 'Dolunay'),
	(7, 'Turkuaz', 'Yildiz'),
	(8, 'Turkuaz', 'Bulut'),
	(9, 'Turkuaz', 'Masal'),
	(10, 'Turkuaz', 'Leyla');
/*!40000 ALTER TABLE `acente` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.bolum
CREATE TABLE IF NOT EXISTS `bolum` (
  `bolum_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `bolum_adi` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`bolum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- tur_acentes.bolum: ~4 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `bolum` DISABLE KEYS */;
INSERT INTO `bolum` (`bolum_id`, `bolum_adi`) VALUES
	(1, 'müdür'),
	(2, 'müsteri temsilcisi'),
	(4, 'Gereksiz biri'),
	(5, 'Dogus DEPO');
/*!40000 ALTER TABLE `bolum` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.dosya
CREATE TABLE IF NOT EXISTS `dosya` (
  `dosya_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `dosya_adi` varchar(30) DEFAULT NULL,
  `dosya_yolu` varchar(200) DEFAULT NULL,
  `dosya_tipi` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dosya_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- tur_acentes.dosya: ~3 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `dosya` DISABLE KEYS */;
INSERT INTO `dosya` (`dosya_id`, `dosya_adi`, `dosya_yolu`, `dosya_tipi`) VALUES
	(50, 'afrika.jpg', 'C:\\Users\\Volkan\\Desktop\\Fotograflar', 'image/jpeg'),
	(52, 'dogu.jpg', 'C:\\Users\\Volkan\\Desktop\\Fotograflar', 'image/jpeg'),
	(53, 'yunanistan.jpg', 'C:\\Users\\Volkan\\Desktop\\Fotograflar', 'image/jpeg');
/*!40000 ALTER TABLE `dosya` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.grup
CREATE TABLE IF NOT EXISTS `grup` (
  `grup_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `grup_adi` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`grup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- tur_acentes.grup: ~3 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `grup` DISABLE KEYS */;
INSERT INTO `grup` (`grup_id`, `grup_adi`) VALUES
	(1, 'Super Admin '),
	(3, 'Ziyaretçi'),
	(5, 'Admin');
/*!40000 ALTER TABLE `grup` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.iletisim
CREATE TABLE IF NOT EXISTS `iletisim` (
  `iletisim_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tel` bigint(20) DEFAULT NULL,
  `e_posta` varchar(30) DEFAULT NULL,
  `musteri_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`iletisim_id`),
  KEY `FK_iletisim_musteri` (`musteri_id`),
  CONSTRAINT `FK_iletisim_musteri` FOREIGN KEY (`musteri_id`) REFERENCES `musteri` (`musteri_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- tur_acentes.iletisim: ~7 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `iletisim` DISABLE KEYS */;
INSERT INTO `iletisim` (`iletisim_id`, `tel`, `e_posta`, `musteri_id`) VALUES
	(7, 5312162540, 'wolkan.1998@hotmail.com', 24),
	(8, 5050012248, 'dogus.ads.1998@hotmail.com', 28),
	(9, 5385678495, 'bunya_ert@hotmail.com', 30),
	(10, 5265958444, 'wolkan_ust@hotmail.com', 31),
	(11, 5050021154, 'dogus@hotmail.com', 34),
	(12, 5515114554, 'bekir.dem@hotmail.com', 35),
	(15, 5123456789, 'ornek@gmail.com', 30);
/*!40000 ALTER TABLE `iletisim` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.kampanya
CREATE TABLE IF NOT EXISTS `kampanya` (
  `kampanya_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `kampanya_turu` varchar(30) DEFAULT NULL,
  `kampanya_detayi` varchar(30) DEFAULT NULL,
  `acente_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`kampanya_id`),
  KEY `FK_kampanya_acente` (`acente_id`),
  CONSTRAINT `FK_kampanya_acente` FOREIGN KEY (`acente_id`) REFERENCES `acente` (`acente_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- tur_acentes.kampanya: ~3 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kampanya` DISABLE KEYS */;
INSERT INTO `kampanya` (`kampanya_id`, `kampanya_turu`, `kampanya_detayi`, `acente_id`) VALUES
	(1, 'NAKİT', 'NAKİTE &20 İNDİRİM', 1),
	(2, 'SEÇKİN', 'KARTA ÖZEL İNDİRİM', 1),
	(3, 'Beleş', 'Beleş', 1);
/*!40000 ALTER TABLE `kampanya` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.musteri
CREATE TABLE IF NOT EXISTS `musteri` (
  `musteri_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `musteri_adi_soyadi` varchar(50) DEFAULT NULL,
  `kampanya_id` bigint(20) unsigned DEFAULT NULL,
  `grup_id` bigint(20) unsigned NOT NULL DEFAULT 3,
  `musteri_sifre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`musteri_id`),
  KEY `FK_musteri_kampanya` (`kampanya_id`),
  KEY `FK_musteri_grup` (`grup_id`),
  CONSTRAINT `FK_musteri_grup` FOREIGN KEY (`grup_id`) REFERENCES `grup` (`grup_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_musteri_kampanya` FOREIGN KEY (`kampanya_id`) REFERENCES `kampanya` (`kampanya_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- tur_acentes.musteri: ~12 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `musteri` DISABLE KEYS */;
INSERT INTO `musteri` (`musteri_id`, `musteri_adi_soyadi`, `kampanya_id`, `grup_id`, `musteri_sifre`) VALUES
	(21, 'Volkan', 1, 3, '1234'),
	(24, 'Volkan Üstekidağ', 2, 3, 'Abcabc1'),
	(26, 'DogusIpeksac', 1, 3, '1234'),
	(27, 'DogusIpeksac', 1, 3, '1234'),
	(28, 'DogusIpeksac', 1, 3, '123'),
	(29, 'Bunyamin', 1, 3, '1234'),
	(30, 'Bunyamin', 1, 3, '1234'),
	(31, 'volkan ustekidag', NULL, 3, '123'),
	(32, 'tuğba günaçgün', 3, 3, '123'),
	(33, 'DogusIpeksac', NULL, 3, '321'),
	(34, 'dogus', NULL, 3, '123'),
	(35, 'Bekir Demir', 2, 3, '123');
/*!40000 ALTER TABLE `musteri` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.otel
CREATE TABLE IF NOT EXISTS `otel` (
  `otel_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `otel_adi` varchar(30) DEFAULT NULL,
  `otel_yildizi` int(11) DEFAULT NULL,
  `acente_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`otel_id`),
  KEY `FK_otel_acente` (`acente_id`),
  CONSTRAINT `FK_otel_acente` FOREIGN KEY (`acente_id`) REFERENCES `acente` (`acente_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- tur_acentes.otel: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `otel` DISABLE KEYS */;
INSERT INTO `otel` (`otel_id`, `otel_adi`, `otel_yildizi`, `acente_id`) VALUES
	(1, 'hilton', 4, 1),
	(2, 'yıldız', 5, 1);
/*!40000 ALTER TABLE `otel` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.personel
CREATE TABLE IF NOT EXISTS `personel` (
  `personel_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `personel_adi_soyadi` varchar(30) DEFAULT NULL,
  `personel_tel` varchar(50) DEFAULT NULL,
  `acente_id` bigint(20) unsigned NOT NULL,
  `grup_id` bigint(20) unsigned NOT NULL,
  `bolum_id` bigint(20) unsigned NOT NULL,
  `personel_sifre` varchar(50) DEFAULT NULL,
  `personel_kullanici` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`personel_id`),
  KEY `FK_personel_acente` (`acente_id`),
  KEY `FK_personel_grup` (`grup_id`),
  KEY `FK_personel_bolum` (`bolum_id`),
  CONSTRAINT `FK_personel_acente` FOREIGN KEY (`acente_id`) REFERENCES `acente` (`acente_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_personel_bolum` FOREIGN KEY (`bolum_id`) REFERENCES `bolum` (`bolum_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_personel_grup` FOREIGN KEY (`grup_id`) REFERENCES `grup` (`grup_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- tur_acentes.personel: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `personel` DISABLE KEYS */;
INSERT INTO `personel` (`personel_id`, `personel_adi_soyadi`, `personel_tel`, `acente_id`, `grup_id`, `bolum_id`, `personel_sifre`, `personel_kullanici`) VALUES
	(4, 'Tuba Günaçgün', '5312162545', 1, 1, 2, '123', 'tubagunacgun'),
	(9, 'ayse', '05511281475', 1, 1, 1, '123', 'muhendis');
/*!40000 ALTER TABLE `personel` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.sehir
CREATE TABLE IF NOT EXISTS `sehir` (
  `sehir_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sehir_adi` varchar(30) DEFAULT NULL,
  `sehir_adres` varchar(30) DEFAULT NULL,
  `sehir_tel` bigint(20) DEFAULT NULL,
  `acente_id` bigint(20) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`sehir_id`),
  KEY `FK_sehir_acente` (`acente_id`),
  CONSTRAINT `FK_sehir_acente` FOREIGN KEY (`acente_id`) REFERENCES `acente` (`acente_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- tur_acentes.sehir: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `sehir` DISABLE KEYS */;
INSERT INTO `sehir` (`sehir_id`, `sehir_adi`, `sehir_adres`, `sehir_tel`, `acente_id`) VALUES
	(11, 'Adana', 'BarajYolu ', 5385678495, 1),
	(12, 'istanbul', 'Üsküdar ', 5326854959, 1);
/*!40000 ALTER TABLE `sehir` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.sigorta
CREATE TABLE IF NOT EXISTS `sigorta` (
  `sigorta_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sigorta_suresi` bigint(20) DEFAULT NULL,
  `sigorta_adi` varchar(50) DEFAULT NULL,
  `acente_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`sigorta_id`),
  KEY `FK_sigorta_acente` (`acente_id`),
  CONSTRAINT `FK_sigorta_acente` FOREIGN KEY (`acente_id`) REFERENCES `acente` (`acente_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- tur_acentes.sigorta: ~4 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `sigorta` DISABLE KEYS */;
INSERT INTO `sigorta` (`sigorta_id`, `sigorta_suresi`, `sigorta_adi`, `acente_id`) VALUES
	(1, 2, 'Yol', 2),
	(2, 12, 'Sağlık', 1),
	(3, 4, 'Aşk acısı', 1),
	(4, 2, 'Kalp yetmezligi', 1);
/*!40000 ALTER TABLE `sigorta` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.tur
CREATE TABLE IF NOT EXISTS `tur` (
  `tur_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tur_adi` varchar(30) DEFAULT NULL,
  `tur_turu` varchar(30) DEFAULT NULL,
  `tur_tarihi` date DEFAULT NULL,
  `acente_id` bigint(20) unsigned NOT NULL,
  `dosya_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`tur_id`),
  KEY `FK_tur_acente` (`acente_id`),
  KEY `FK_tur_dosya` (`dosya_id`),
  CONSTRAINT `FK_tur_acente` FOREIGN KEY (`acente_id`) REFERENCES `acente` (`acente_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tur_dosya` FOREIGN KEY (`dosya_id`) REFERENCES `dosya` (`dosya_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- tur_acentes.tur: ~3 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `tur` DISABLE KEYS */;
INSERT INTO `tur` (`tur_id`, `tur_adi`, `tur_turu`, `tur_tarihi`, `acente_id`, `dosya_id`) VALUES
	(25, 'Afrika Turu', 'Yurtdışı', '2019-10-10', 6, 50),
	(27, 'Doğu Turu', 'Yurtiçi', '2019-10-10', 2, 52),
	(28, 'Yunanistan Turu', 'Yurtiçi', '2019-10-10', 2, 53);
/*!40000 ALTER TABLE `tur` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.tur_musteri
CREATE TABLE IF NOT EXISTS `tur_musteri` (
  `musteri_id` bigint(20) unsigned NOT NULL,
  `tur_id` bigint(20) unsigned NOT NULL,
  KEY `FK_tur_musteri_tur` (`tur_id`),
  KEY `FK_tur_musteri_musteri` (`musteri_id`),
  CONSTRAINT `FK_tur_musteri_musteri` FOREIGN KEY (`musteri_id`) REFERENCES `musteri` (`musteri_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tur_musteri_tur` FOREIGN KEY (`tur_id`) REFERENCES `tur` (`tur_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- tur_acentes.tur_musteri: ~3 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `tur_musteri` DISABLE KEYS */;
INSERT INTO `tur_musteri` (`musteri_id`, `tur_id`) VALUES
	(28, 27),
	(28, 28),
	(24, 28);
/*!40000 ALTER TABLE `tur_musteri` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.tur_personel
CREATE TABLE IF NOT EXISTS `tur_personel` (
  `tur_id` bigint(20) unsigned NOT NULL,
  `personel_id` bigint(20) unsigned NOT NULL,
  KEY `FK_tur_personel_tur` (`tur_id`),
  KEY `FK_tur_personel_personel` (`personel_id`),
  CONSTRAINT `FK_tur_personel_personel` FOREIGN KEY (`personel_id`) REFERENCES `personel` (`personel_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tur_personel_tur` FOREIGN KEY (`tur_id`) REFERENCES `tur` (`tur_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- tur_acentes.tur_personel: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `tur_personel` DISABLE KEYS */;
INSERT INTO `tur_personel` (`tur_id`, `personel_id`) VALUES
	(25, 4);
/*!40000 ALTER TABLE `tur_personel` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.ulasim
CREATE TABLE IF NOT EXISTS `ulasim` (
  `ulasim_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ulasim_kalkis_yeri` varchar(30) DEFAULT NULL,
  `ulasim_varis_yeri` varchar(30) DEFAULT NULL,
  `ulasim_turu` varchar(50) DEFAULT NULL,
  `acente_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`ulasim_id`),
  KEY `FK_ulasim_acente` (`acente_id`),
  CONSTRAINT `FK_ulasim_acente` FOREIGN KEY (`acente_id`) REFERENCES `acente` (`acente_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- tur_acentes.ulasim: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `ulasim` DISABLE KEYS */;
INSERT INTO `ulasim` (`ulasim_id`, `ulasim_kalkis_yeri`, `ulasim_varis_yeri`, `ulasim_turu`, `acente_id`) VALUES
	(1, 'İstanbul', 'Malatya', 'Uçak', 1),
	(4, 'Malatya', 'Adana', 'Uçak', 1);
/*!40000 ALTER TABLE `ulasim` ENABLE KEYS */;

-- tablo yapısı dökülüyor tur_acentes.yetkilendirme
CREATE TABLE IF NOT EXISTS `yetkilendirme` (
  `yetkilendirme_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `y_bolum_adi` varchar(30) DEFAULT NULL,
  `grup_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`yetkilendirme_id`),
  KEY `FK_yetkilendirme_grup` (`grup_id`),
  CONSTRAINT `FK_yetkilendirme_grup` FOREIGN KEY (`grup_id`) REFERENCES `grup` (`grup_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- tur_acentes.yetkilendirme: ~10 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `yetkilendirme` DISABLE KEYS */;
INSERT INTO `yetkilendirme` (`yetkilendirme_id`, `y_bolum_adi`, `grup_id`) VALUES
	(9, 'Update', 1),
	(10, 'Delete', 1),
	(11, 'Read', 1),
	(19, 'Create', 5),
	(20, 'Read', 5),
	(21, 'Delete', 5),
	(22, 'Update', 5),
	(23, 'Access', 1),
	(24, 'Create', 1),
	(25, 'Update', 1);
/*!40000 ALTER TABLE `yetkilendirme` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
