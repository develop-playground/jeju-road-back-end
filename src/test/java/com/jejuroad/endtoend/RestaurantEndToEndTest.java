package com.jejuroad.endtoend;

import com.jejuroad.common.HttpResponseBody;
import com.jejuroad.common.Message;
import com.jejuroad.domain.restaurant.Category;
import com.jejuroad.domain.restaurant.Tip;
import com.jejuroad.dto.RestaurantRequest;
import com.jejuroad.dto.RestaurantResponse;
import com.jejuroad.repository.CategoryRepository;
import com.jejuroad.repository.TipRepository;
import com.jejuroad.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import java.time.LocalTime;
import java.util.List;

import static com.jejuroad.common.Message.COMMON_RESPONSE_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class RestaurantEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    private WebTestClient webTestClient;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TipRepository tipRepository;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        webTestClient = MockMvcWebTestClient
            .bindTo(mockMvc)
            .filter(
                documentationConfiguration(restDocumentation)
                    .operationPreprocessors()
                    .withResponseDefaults(prettyPrint())
            )
            .build();
    }

    @Test
    @DisplayName("맛집 등록 성공 테스트")
    void testRegisteringRestaurant() {
        // given
        categoryRepository.save(new Category(0L, "RESTAURANT"));
        categoryRepository.save(new Category(0L, "CAFFE"));

        tipRepository.save(new Tip(0L, "주변 공영 주차장 이용 가능"));
        tipRepository.save(new Tip(0L, "합석 가능성 있음"));

        final Message expectedMessage = COMMON_RESPONSE_OK;
        final RestaurantResponse.Register expectedInformation = new RestaurantResponse.Register(1L);
        final RestaurantRequest.Register requestDto = RestaurantRequest.Register.builder()
            .name("우진해장국")
            .categories(List.of("RESTAURANT", "CAFFE"))
            .introduction("'수요미식회'에 방영된, 따끈한 국물 요리로 해장하기 좋은 음식점")
            .wayToGo("동문 재래 시장에서 도보 9분")
            .zipcode("28921")
            .state("제주")
            .city("제주시")
            .simpleAddress("제주 시내")
            .detailAddress("제주특별자치도 제주시 서사로 11")
            .latitude(11.11)
            .longitude(22.22)
            .tipIds(List.of(1L, 2L))
            .openTimes(List.of(
                new RestaurantRequest.Register.OpenTime(
                    "MON",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                ),
                new RestaurantRequest.Register.OpenTime(
                    "TUE",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                ),
                new RestaurantRequest.Register.OpenTime(
                    "WED",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                ),
                new RestaurantRequest.Register.OpenTime(
                    "THU",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                ),
                new RestaurantRequest.Register.OpenTime(
                    "FRI",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                ),
                new RestaurantRequest.Register.OpenTime(
                    "SAT",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                )
            ))
            .images(List.of(
                "image/001.jpg",
                "image/002.jpg",
                "image/003.jpg"
            ))
            .build();

        // when & then
        webTestClient
            .post()
            .uri("/api/restaurants")
            .accept(APPLICATION_JSON)
            .bodyValue(requestDto)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody(new ParameterizedTypeReference<HttpResponseBody<RestaurantResponse.Register>>() {
            })
            .consumeWith(response -> {
                HttpResponseBody<RestaurantResponse.Register> responseBody = response.getResponseBody();
                assertThat(responseBody.getCode()).isEqualTo(expectedMessage.getCode());
                assertThat(responseBody.getMessage()).isEqualTo(expectedMessage.getMessage());
                assertThat(responseBody.getInformation()).isEqualTo(expectedInformation);
            })
            .consumeWith(
                document(
                    "restaurants/register",
                    requestFields(
                        fieldWithPath("name").description("맛집의 이름"),
                        fieldWithPath("categories").description("맛집의 종류"),
                        fieldWithPath("introduction").description("맛집 소개"),
                        fieldWithPath("wayToGo").description("가는방법"),
                        fieldWithPath("zipcode").description("우편번호"),
                        fieldWithPath("state").description("광역지방자치단체(시/도)"),
                        fieldWithPath("city").description("기초지방자치단체(시/군/구)"),
                        fieldWithPath("simpleAddress").description("간단한 주소"),
                        fieldWithPath("detailAddress").description("상세 주소"),
                        fieldWithPath("latitude").description("위도"),
                        fieldWithPath("longitude").description("경도"),
                        fieldWithPath("tipIds").description("이용팁의 식별자"),
                        subsectionWithPath("openTimes").description("이용가능시간"),
                        fieldWithPath("openTimes.[].day").description("영업일의 요일"),
                        fieldWithPath("openTimes.[].operationStart").description("영업 시작시간"),
                        fieldWithPath("openTimes.[].operationEnd").description("영업 종료시간"),
                        fieldWithPath("openTimes.[].breakStart").description("브레이크타임 시작시간"),
                        fieldWithPath("openTimes.[].breakEnd").description("브레이크타임 종료시간"),
                        fieldWithPath("images").description("맛집의 사진 목록")
                    ),
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        fieldWithPath("id").description("등록된 맛집의 식별자")
                    )
                )
            );
    }

}
