package com.jejuroad;

import com.jejuroad.common.HttpResponseBody;
import com.jejuroad.common.Message;
import com.jejuroad.dto.*;
import com.jejuroad.service.CategoryService;
import com.jejuroad.service.MenuService;
import com.jejuroad.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@WebMvcTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class RestaurantWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private MenuService menuService;

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
            .tipIds(List.of(1L, 2L))
            .openTimes(List.of(
                new RestaurantRequest.Register.OpenTime(
                    "MONDAY",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                ),
                new RestaurantRequest.Register.OpenTime(
                    "MONDAY",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                ),
                new RestaurantRequest.Register.OpenTime(
                    "TUESDAY",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                ),
                new RestaurantRequest.Register.OpenTime(
                    "WEDNESDAY",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                ),
                new RestaurantRequest.Register.OpenTime(
                    "THURSDAY",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                ),
                new RestaurantRequest.Register.OpenTime(
                    "FRIDAY",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                ),
                new RestaurantRequest.Register.OpenTime(
                    "SATURDAY",
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(21, 0, 0),
                    LocalTime.of(14, 0, 0),
                    LocalTime.of(16, 0, 0)
                )
            ))
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
                        fieldWithPath("longitude").description("경도"),
                        fieldWithPath("tipIds").description("이용팁의 식별자"),
                        subsectionWithPath("openTimes").description("이용가능시간"),
                        fieldWithPath("openTimes.[].day").description("영업일의 요일"),
                        fieldWithPath("openTimes.[].operationStart").description("영업 시작시간"),
                        fieldWithPath("openTimes.[].operationEnd").description("영업 종료시간"),
                        fieldWithPath("openTimes.[].breakStart").description("브레이크타임 시작시간"),
                        fieldWithPath("openTimes.[].breakEnd").description("브레이크타임 종료시간")
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
        final List<RestaurantResponse.Find> expectedResult = List.of(
            new RestaurantResponse.Find(
                15L,
                "우진해장국",
                List.of("RESTAURANT", "CAFFE"),
                "제주 시내",
                "/images/image_001",
                "'수요미식회'에 방영된, 따끈한 국물 요리로 해장하기 좋은 음식점"
            ),
            new RestaurantResponse.Find(
                14L,
                "원 앤 온리",
                List.of("CAFFE"),
                "중문",
                "/images/image_017",
                "'배틀트립'에서 다녀간, '산방산'의 이국적인 뷰를 감상할 수 있는 카페"
            ),
            new RestaurantResponse.Find(
                13L,
                "흑본오겹 함덕점",
                List.of("RESTAURANT"),
                "조천/구좌",
                "/images/image_021",
                "제주산 흑돼지고기와 특수부위를 맛볼 수 있는 숯불구이 전문점"
            ),
            new RestaurantResponse.Find(
                12L,
                "오는 정 김밥",
                List.of("RESTAURANT"),
                "서귀포",
                "/images/image_031",
                "많은 사람들이 줄 서 기다리는, '맛잇는 녀석들' 출연 김밥 맛집"
            ),
            new RestaurantResponse.Find(
                11L,
                "자매국수 본점",
                List.of("RESTAURANT"),
                "제주 시내",
                "/images/image_002",
                "쫄깃한 흑돼지 수육을 올린 국수로 인기 있는 체인 음식점"
            )
        );
        final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by(DESC, "id"));
        final Page<RestaurantResponse.Find> page = new PageImpl<>(expectedResult, pageRequest, 20);

        when(restaurantService.find(pageRequest)).thenReturn(page);

        webTestClient
            .get()
            .uri("/api/restaurants?page=1&size=5&sort=id,DESC")
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody(new ParameterizedTypeReference<HttpResponseBody<Object>>() {
            })
            .consumeWith(response -> {
                HttpResponseBody<Object> responseBody = response.getResponseBody();
                assertThat(responseBody.getCode()).isEqualTo(expectedMessage.getCode());
                assertThat(responseBody.getMessage()).isEqualTo(expectedMessage.getMessage());
//                assertThat(responseBody.getInformation()).isEqualTo(expectedResult);
            })
            .consumeWith(
                document(
                    "restaurants/findAll",
                    requestParameters(
                        parameterWithName("page").description("페이지 번호").optional(),
                        parameterWithName("size").description("페이지의 크기").optional(),
                        parameterWithName("sort").description("정렬 기준이 되는 속성 및 정렬 방향").optional()
                    ),
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        subsectionWithPath("links").description("페이징 링크"),
                        fieldWithPath("content.[].id").description("맛집 식별자"),
                        fieldWithPath("content.[].name").description("맛집의 이름"),
                        fieldWithPath("content.[].categories").description("맛집의 종류"),
                        fieldWithPath("content.[].address").description("맛집의 간단한 주소"),
                        fieldWithPath("content.[].image").description("맛집의 대표 사진"),
                        fieldWithPath("content.[].introduction").description("맛집 소개글"),
                        fieldWithPath("content.[].links").description("???"),
                        subsectionWithPath("page").description("페이징 관련 메타 정보"),
                        fieldWithPath("page.size").description("페이지의 크기"),
                        fieldWithPath("page.totalElements").description("전체 원소의 개수"),
                        fieldWithPath("page.totalPages").description("전체 페이지의 개수"),
                        fieldWithPath("page.number").description("현재 페이지 번호")
                    )
                )
            );

        verify(restaurantService).find(pageRequest);
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
                        fieldWithPath("wayToGo").description("가는 방법"),
                        fieldWithPath("simpleAddress").description("간단한 주소"),
                        fieldWithPath("detailAddress").description("상세 주소"),
                        subsectionWithPath("openTimes").description("이용가능시간"),
                        fieldWithPath("openTimes.[].day").description("요일"),
                        fieldWithPath("openTimes.[].servingTime").description("개장 시간"),
                        fieldWithPath("openTimes.[].breakTime").description("브레이크 타임"),
                        fieldWithPath("introduction").description("맛집 소개글"),
                        fieldWithPath("tips").description("이용팁")
                    )
                )
            );

        verify(restaurantService).findById(1L);
    }

    @Test
    @DisplayName("카테고리 목록 조회 성공 테스트")
    void testFindCategories() {
        final Message expectedMessage = COMMON_RESPONSE_OK;
        final List<CategoryResponse.Find> expectedInformation = List.of(
            new CategoryResponse.Find(
                "CAFFE"
            ),
            new CategoryResponse.Find(
                "RESTAURANT"
            )
        );

        when(categoryService.find()).thenReturn(expectedInformation);

        webTestClient
            .get()
            .uri("/api/restaurants/categories")
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody(new ParameterizedTypeReference<HttpResponseBody<List<CategoryResponse.Find>>>() {
            })
            .consumeWith(response -> {
                HttpResponseBody<List<CategoryResponse.Find>> responseBody = response.getResponseBody();
                assertThat(responseBody.getCode()).isEqualTo(expectedMessage.getCode());
                assertThat(responseBody.getMessage()).isEqualTo(expectedMessage.getMessage());
                assertThat(responseBody.getInformation()).isEqualTo(expectedInformation);
            })
            .consumeWith(
                document(
                    "categories/findAll",
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        fieldWithPath("name").description("카테고리의 이름")
                    )
                )
            );

        verify(categoryService).find();
    }

    @Test
    @DisplayName("메뉴 등록 성공 테스트")
    void testRegisteringMenu() {
        final Message expectedMessage = COMMON_RESPONSE_OK;
        final MenuResponse.Register expectedInformation = new MenuResponse.Register(1L);
        final MenuRequest.Register requestDto = MenuRequest.Register.builder()
            .name("고사리 해장국")
            .image("/images/image_003")
            .price(9000)
            .restaurantId(1L)
            .build();

        when(menuService.register(requestDto)).thenReturn(expectedInformation);

        webTestClient
            .post()
            .uri("/api/menus")
            .accept(APPLICATION_JSON)
            .bodyValue(requestDto)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody(new ParameterizedTypeReference<HttpResponseBody<MenuResponse.Register>>() {
            })
            .consumeWith(response -> {
                HttpResponseBody<MenuResponse.Register> responseBody = response.getResponseBody();
                assertThat(responseBody.getCode()).isEqualTo(expectedMessage.getCode());
                assertThat(responseBody.getMessage()).isEqualTo(expectedMessage.getMessage());
                assertThat(responseBody.getInformation()).isEqualTo(expectedInformation);
            })
            .consumeWith(
                document(
                    "menus/register",
                    requestFields(
                        fieldWithPath("name").description("메뉴 이름"),
                        fieldWithPath("price").description("메뉴 가격"),
                        fieldWithPath("image").description("메뉴 이미지 경로"),
                        fieldWithPath("restaurantId").description("메뉴가 등록될 맛집 식별자")
                    ),
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        fieldWithPath("id").description("등록된 메뉴 식별자")
                    )
                )
            );

        verify(menuService).register(requestDto);
    }

}
