package com.gucardev.trakyabilmuhbe.config;

import com.gucardev.trakyabilmuhbe.model.Announcement;
import com.gucardev.trakyabilmuhbe.model.Role;
import com.gucardev.trakyabilmuhbe.model.User;
import com.gucardev.trakyabilmuhbe.model.Message;
import com.gucardev.trakyabilmuhbe.request.ChannelRequest;
import com.gucardev.trakyabilmuhbe.service.AnnouncementService;
import com.gucardev.trakyabilmuhbe.service.ChannelService;
import com.gucardev.trakyabilmuhbe.service.MessageService;
import com.gucardev.trakyabilmuhbe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Slf4j
@Component
public class StartupConfig implements CommandLineRunner {

    private final UserService userService;
    private final ChannelService channelService;
    private final AnnouncementService announcementService;
    private final MessageService messageService;

    @Override
    public void run(String... args) throws Exception {
        log.debug("user is creating!");
        User admin = userService.create(User.builder().name("Gurkan").surname("UCAR").role(Role.ADMIN)
                .password("pass")
                .mail("gurkanucar@traky@.edu.tr")
                .approved(true).username("gurkan").build());
        User user1 = userService.create(User.builder().name("Ahmet").surname("MEHMET").role(Role.ACADEMICIAN)
                .password("pass")
                .mail("amehmet@traky@.edu.tr")
                .approved(true).username("amehmet").build());
        User user2 = userService.create(User.builder().name("Mehmet").surname("YILMAZ").role(Role.ACADEMICIAN)
                .password("pass")
                .mail("my@traky@.edu.tr")
                .approved(true).username("myilmaz").build());


        var channelJava = channelService.create(ChannelRequest.builder().channelName("Ileri Java Dersi").user(admin).canSendOthers(false).build());
        var channelStaj = channelService.create(ChannelRequest.builder().channelName("Staj kanali").user(user1).canSendOthers(true).build());
        var channelIs = channelService.create(ChannelRequest.builder().channelName("Is Kanali").canSendOthers(true).user(user2).build());
        var channelGorsel = channelService.create(ChannelRequest.builder().channelName("Gorsel Programlama Dersi").canSendOthers(false).user(admin).build());
        var channelMobil = channelService.create(ChannelRequest.builder().channelName("Mobil Uygulama Dersi").canSendOthers(false).user(user2).build());
        var channelVeriTabani = channelService.create(ChannelRequest.builder().channelName("Veri Tabani Dersi").canSendOthers(false).user(user2).build());


        messageService.create(Message.builder().user(user1).channel(channelStaj).content("XYZ firmasinda staj firsati!").link("https://www.javatpoint.com/spring-boot-aop-before-advice").build());
        messageService.create(Message.builder().user(user2).channel(channelStaj).content("Teknoparktaki ABC firmsi stajyer aramaktadir").link("https://www.javatpoint.com/spring-boot-aop-before-advice").build());
        messageService.create(Message.builder().user(user1).channel(channelStaj).content("ZXC firmasi yari zamanli stajyer aramaktadir.").link("https://www.javatpoint.com/spring-boot-aop-before-advice").build());
        messageService.create(Message.builder().user(admin).channel(channelStaj).content("QWE Bank stajyer aliyor.").link("https://www.javatpoint.com/spring-boot-aop-before-advice").build());
        messageService.create(Message.builder().user(admin).channel(channelJava).content("java dersi iptal").build());
        messageService.create(Message.builder().user(admin).channel(channelJava).content("Yarin javada nasil json parse edebilecegimizi gorecegiz. Lutfen linki inceleyin").link("kaynak").build());
        messageService.create(Message.builder().user(admin).channel(channelJava).content("Yarin java swing kullanimini gorecegiz").build());
        messageService.create(Message.builder().user(user2).channel(channelIs).content("XYZ firmasinda parttime olarak is firsati").link("https://github.com/gurkanucar").build());
        messageService.create(Message.builder().user(admin).channel(channelGorsel).content("Gorsel Dersi icin github linki").link("https://github.com/gurkanucar").build());
        messageService.create(Message.builder().user(user2).channel(channelMobil).content("Mobil dersi a ve b gruplari birlesti").build());
        messageService.create(Message.builder().user(user2).channel(channelVeriTabani).content("Veri tabani icin kaynkalar teams de paylasildi").build());

        announcementService.create(Announcement.builder().title("BİL413 İŞ SAĞLIĞI VE GÜVENLİĞİ I Hk.").link("https://bilmuh.trakya.edu.tr/news/bil413-is-sagligi-ve-guvenligi-i-hk").content("Mühendislik Fakültesi BİL413 İŞ SAĞLIĞI VE GÜVENLİĞİ I adlı ders fakülte ortak ders kapsamında online olarak gerçekleştirilecektir. Bu nedenle bu dersi alan Bilgisayar Mühendisliği bölüm öğrencileri de TEAMS'de MUH002_ELEKTRİK-ELEKTRONİK MÜHENDİSLİĞİ_3 adlı ekipte derslerini ve sınavlarını takip edeceklerdir.").build());
        announcementService.create(Announcement.builder().title("Matematik I dersi hakkında").link("https://bilmuh.trakya.edu.tr/news/matematik-i-dersi-hakkinda").content("Bilgisayar Mühendisliği Matematik I dersi Matematik Bölümünde yapılacaktır.").build());
        announcementService.create(Announcement.builder().title("Ders ve Staj Muafiyetleri Hakkında").link("https://bilmuh.trakya.edu.tr/news/ders-ve-staj-muafiyetleri-hakkinda").content("2022-2023 eğitim öğretim yılı güz yarıyılı için Ders ve Staj muafiyet dilekçeleri 19-23 Eylül 2022 tarihleri arasında verilecektir. Bu tarihten sonra verilen dilekçeler işleme alınmayacaktır.").build());
        announcementService.create(Announcement.builder().title("4. Sınıf Bilgisayar Mühendisliği Öğrencilerinin Dikkatine").link("https://bilmuh.trakya.edu.tr/news/4--sinif-bilgisayar-muhendisligi-ogrencilerinin-dikkatine").content("İş sağlığı ve güvenliği dersi seçemeyen öğrencilerin bu dersi eklemeleri gerekmektedir. ").build());
        announcementService.create(Announcement.builder().title("2022-2023 Eğitim Öğretim Yılı Güz Dönemi Ders Programı (Güncel)").link("https://bilmuh.trakya.edu.tr/news/2022-2023-egitim-ogretim-yili-guz-donemi-ders-programi--guncel").content("2022-2023 Eğitim Öğretim Yılı Güz Dönemi Ders Programı ektedir.\n" +
                "\n" +
                "Not: Öğrenci numarası tek sayı ile bitenler A şube, çift sayı ile bitenler B şube olan dersleri seçmelidir. \n" +
                "4. sınıf öğrencileri derslerini çakışmayacak şekilde seçmelidir.").build());
        announcementService.create(Announcement.builder().title("2021-2022 Eğitim Öğretim Yılı Yaz Okulu Final Sınav Programı").link("https://bilmuh.trakya.edu.tr/news/2021-2022-egitim-ogretim-yili-yaz-okulu-final-sinav-programi").content("2021-2022 Eğitim Öğretim Yılı Yaz Okulu final sınav programı ektedir. ").build());
        announcementService.create(Announcement.builder().title("Visys Makine Yazılımcı Arıyor").link("https://bilmuh.trakya.edu.tr/news/visys-makine-yazilimci-ariyor").content("Visys Makine şirketi Edirne Teknopark’taki ofisinde görevlendirilmek üzere yazılımcı aramaktadır. Detaylı bilgi için bağlantıya tıklayabilirsiniz. ").build());
    }
}
