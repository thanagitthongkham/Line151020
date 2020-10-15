package com.cpfit.line.flex;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.action.URIAction.AltUri;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.*;
import com.linecorp.bot.model.message.flex.component.Button.ButtonHeight;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectMode;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectRatio;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Bubble.BubbleSize;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

import java.util.function.Supplier;

import static java.util.Arrays.asList;

import java.net.URI;
import java.net.URISyntaxException;

public class RestaurantFlexMessageSupplier implements Supplier<FlexMessage> {
    @Override
    public FlexMessage get() {
        Image heroBlock;
        Bubble bubbleContainer=null;
		try {
			heroBlock = createHeroBlock();
			 final Box bodyBlock = createBodyBlock();
		        final Box footerBlock = createFooterBlock();

		          bubbleContainer = Bubble.builder()
		                .hero(heroBlock)
		                .body(bodyBlock)
		                .footer(footerBlock)
		                .size(BubbleSize.KILO)
		                .build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return new FlexMessage("Restaurant", bubbleContainer);
    }

    private Image createHeroBlock() throws URISyntaxException {
        return Image.builder()
                .url(new URI("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/cafe.png"))
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(ImageAspectRatio.R20TO13)
                .aspectMode(ImageAspectMode.Cover)
                .action(new URIAction("label",new URI("http://example.com"),new AltUri(new URI("http://example.com"))))
                .build();
    }

    private Box createBodyBlock() throws URISyntaxException {
        final Text title = Text.builder()
                .text("Brown Cafe")
                .weight(Text.TextWeight.BOLD)
                .size(FlexFontSize.XL)
                .build();
        final Box review = createReviewBox();
        final Box info = createInfoBox();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(asList(title, review, info))
                .build();
    }

    private Box createInfoBox() {
        final Box place = Box.builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(asList(
                        Text.builder()
                            .text("Place")
                            .color("#aaaaaa")
                            .size(FlexFontSize.SM)
                            .flex(1)
                            .build(),
                        Text.builder()
                            .text("Silom, Bangkok")
                            .wrap(true)
                            .color("#666666")
                            .flex(5)
                            .build()
                )).build();
        final Box time = Box.builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(asList(
                        Text.builder().text("Time")
                            .color("#aaaaaa")
                            .size(FlexFontSize.SM)
                            .flex(1)
                            .build(),
                        Text.builder()
                            .text("10:00 - 23:00")
                            .wrap(true)
                            .color("#666666")
                            .size(FlexFontSize.SM)
                            .flex(5)
                            .build()
                )).build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .margin(FlexMarginSize.LG)
                .spacing(FlexMarginSize.SM)
                .contents(asList(place, time))
                .build();
    }

    private Box createReviewBox() throws URISyntaxException {
        final Icon goldStar = Icon.builder()
                .size(FlexFontSize.SM)
                .url(new URI("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/gold_star.png"))
                .build();
        final Icon grayStar = Icon.builder()
                .size(FlexFontSize.SM)
                .url(new URI("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/gray_star.png"))
                .build();
        final Text point = Text.builder()
                .text("4.0")
                .size(FlexFontSize.SM)
                .color("#999999")
                .margin(FlexMarginSize.MD)
                .flex(0)
                .build();

        return Box.builder()
                .layout(FlexLayout.BASELINE)
                .margin(FlexMarginSize.MD)
                .contents(asList(goldStar, goldStar, goldStar, goldStar, grayStar, point))
                .build();
    }

    private Box createFooterBlock() throws URISyntaxException {
        final Spacer spacer = Spacer.builder().size(FlexMarginSize.SM).build();
        final Button callAction = Button.builder()
                .style(Button.ButtonStyle.LINK)
                .height(ButtonHeight.MEDIUM)
               // .action(new URIAction("CALL", "tel:00000"))

                .action(new URIAction("CAll",new URI("tel:00000"),new AltUri(new URI("tel:00000"))))
                .build();
        final Separator separator = Separator.builder().build();
        final Button websiteAction = Button.builder()
                .style(Button.ButtonStyle.LINK)
                .height(ButtonHeight.SMALL)
                //.action(new URIAction("WEBSITE", "https://example.com"))
                .action(new URIAction("WEBSITE",new URI("http://example.com"),new AltUri(new URI("http://example.com"))))
                .build();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.SM)
                .contents(asList(spacer, callAction, separator, websiteAction))
                .build();

    }
}
