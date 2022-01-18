package com.jejuroad;

import com.jejuroad.common.HttpResponseBody;
import com.jejuroad.common.Message;
import com.jejuroad.dto.RestaurantRequest;
import com.jejuroad.dto.RestaurantResponse;
import com.jejuroad.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import java.util.List;

import static com.jejuroad.common.Message.COMMON_RESPONSE_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@WebMvcTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        webTestClient = MockMvcWebTestClient
            .bindTo(mockMvc)
            .filter(
                documentationConfiguration(restDocumentation)
                    .operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint()))
            .build();
    }

    @Test
    @DisplayName("맛집 등록 성공 테스트")
    void testRegisteringRestaurant() {
        final Message expectedMessage = COMMON_RESPONSE_OK;
        final RestaurantResponse.Register expectedInformation = new RestaurantResponse.Register(1L);
        final RestaurantRequest.Register requestDto = RestaurantRequest.Register.builder()
            .name("우진해장국")
            .categories(List.of("restaurant", "caffe"))
            .introduction("'수요미식회'에 방영된, 따끈한 국물 요리로 해장하기 좋은 음식점")
            .wayToGo("동문 재래 시장에서 도보 9분")
            .zipcode("28921")
            .state("제주")
            .city("제주시")
            .simpleAddress("제주 시내")
            .detailAddress("제주특별자치도 제주시 서사로 11")
            .latitude(11.11)
            .longitude(22.22)
            .build();

        when(restaurantService.register(requestDto)).thenReturn(expectedInformation);

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
                        fieldWithPath("longitude").description("경도")
                    ),
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        fieldWithPath("id").description("등록된 맛집의 식별자")
                    )
                )
            );

        verify(restaurantService).register(requestDto);
    }

    @Test
    @DisplayName("맛집 목록 조회 성공 테스트")
    void testFindRestaurants() {
        final Message expectedMessage = COMMON_RESPONSE_OK;
        final List<RestaurantResponse.Find> expectedInformation = List.of(
            new RestaurantResponse.Find(
                1L,
                "우진해장국",
                List.of("RESTAURANT", "CAFFE"),
                "제주 시내",
                "/images/image_001",
                "'수요미식회'에 방영된, 따끈한 국물 요리로 해장하기 좋은 음식점"
            ),
            new RestaurantResponse.Find(
                2L,
                "원 앤 온리",
                List.of("CAFFE"),
                "중문",
                "/images/image_017",
                "'배틀트립'에서 다녀간, '산방산'의 이국적인 뷰를 감상할 수 있는 카페"
            )
        );

        when(restaurantService.find()).thenReturn(expectedInformation);

        webTestClient
            .get()
            .uri("/api/restaurants")
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody(new ParameterizedTypeReference<HttpResponseBody<List<RestaurantResponse.Find>>>() {
            })
            .consumeWith(response -> {
                HttpResponseBody<List<RestaurantResponse.Find>> responseBody = response.getResponseBody();
                assertThat(responseBody.getCode()).isEqualTo(expectedMessage.getCode());
                assertThat(responseBody.getMessage()).isEqualTo(expectedMessage.getMessage());
                assertThat(responseBody.getInformation()).isEqualTo(expectedInformation);
            })
            .consumeWith(
                document(
                    "restaurants/findAll",
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        fieldWithPath("id").description("맛집 식별자"),
                        fieldWithPath("name").description("맛집의 이름"),
                        fieldWithPath("categories").description("맛집의 종류"),
                        fieldWithPath("address").description("맛집의 간단한 주소"),
                        fieldWithPath("image").description("맛집의 대표 사진"),
                        fieldWithPath("introduction").description("맛집 소개글")
                    )
                )
            );

        verify(restaurantService).find();
    }

    @Test
    @DisplayName("특정 맛집 상세 조회")
    void testFindById() {
        final Message expectedMessage = COMMON_RESPONSE_OK;
        final RestaurantResponse.FindWithDetail expectedInformation =
            RestaurantResponse.FindWithDetail.builder()
                .id(1L)
                .name("우진해장국")
                .images(List.of("/images/image_001", "/images/image_002"))
                .menus(List.of(
                    new RestaurantResponse.FindWithDetail.Menu(1L, "고사리 해장국", 9000, "/images/image_003"),
                    new RestaurantResponse.FindWithDetail.Menu(2L, "몸국", 9000, "/images/image_004")
                ))
                .wayToGo("동문 재래 시장에서 도보 9분")
                .simpleAddress("제주 시내")
                .detailAddress("제주특별자치도 제주시 서사로 111")
                .openTimes(List.of(
                    new RestaurantResponse.FindWithDetail.OpenTime("MON", "08:00-22:00", "15:00-17:00"),
                    new RestaurantResponse.FindWithDetail.OpenTime("TUE", "08:00-22:00", "15:00-17:00"),
                    new RestaurantResponse.FindWithDetail.OpenTime("WED", "08:00-22:00", "15:00-17:00"),
                    new RestaurantResponse.FindWithDetail.OpenTime("THU", "08:00-22:00", "15:00-17:00"),
                    new RestaurantResponse.FindWithDetail.OpenTime("SAT", "08:00-22:00", "15:00-17:00"),
                    new RestaurantResponse.FindWithDetail.OpenTime("SUN", "08:00-22:00", "15:00-17:00")
                ))
                .introduction("'수요미식회'에 방영된, 따끈한 국물 요리로 해장하기 좋은 음식점")
                .tips(List.of(
                    "주변 공영 주차장 이용 가능",
                    "합석 가능성 있음"
                ))
                .build();


        when(restaurantService.findById(1L)).thenReturn(expectedInformation);

        webTestClient
            .get()
            .uri("/api/restaurants/{id}", 1L)
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody(new ParameterizedTypeReference<HttpResponseBody<RestaurantResponse.FindWithDetail>>() {
            })
            .consumeWith(response -> {
                HttpResponseBody<RestaurantResponse.FindWithDetail> responseBody = response.getResponseBody();
                assertThat(responseBody.getCode()).isEqualTo(expectedMessage.getCode());
                assertThat(responseBody.getMessage()).isEqualTo(expectedMessage.getMessage());
                assertThat(responseBody.getInformation()).isEqualTo(expectedInformation);
            })
            .consumeWith(
                document(
                    "restaurants/findById",
                    pathParameters(
                        parameterWithName("id").description("맛집 식별자")
                    ),
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        fieldWithPath("id").description("맛집 식별자"),
                        fieldWithPath("name").description("맛집의 이름"),
                        fieldWithPath("images").description("맛집의 사진 목록"),
                        subsectionWithPath("menus").description("메뉴 목록"),
                        fieldWithPath("menus.[].id").description("메뉴 식별자"),
                        fieldWithPath("menus.[].name").description("메뉴 이름"),
                        fieldWithPath("menus.[].price").description("메뉴 갸격"),
                        fieldWithPath("menus.[].image").description("메뉴 이미지 경로"),
                        fieldWithPath("way_to_go").description("가는 방법"),
                        fieldWithPath("simple_address").description("간단한 주소"),
                        fieldWithPath("detail_address").description("상세 주소"),
                        subsectionWithPath("open_times").description("이용가능시간"),
                        fieldWithPath("open_times.[].day").description("요일"),
                        fieldWithPath("open_times.[].serving_time").description("개장 시간"),
                        fieldWithPath("open_times.[].break_time").description("브레이크 타임"),
                        fieldWithPath("introduction").description("맛집 소개글"),
                        fieldWithPath("tips").description("이용팁")
                    )
                )
            );

        verify(restaurantService).findById(1L);
    }


}
