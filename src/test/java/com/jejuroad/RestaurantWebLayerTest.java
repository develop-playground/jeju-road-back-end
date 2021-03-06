package com.jejuroad;

import com.jejuroad.common.HttpResponseBody;
import com.jejuroad.common.Message;
import com.jejuroad.dto.*;
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
    @DisplayName("?????? ?????? ?????? ?????????")
    void testRegisteringRestaurant() {
        final Message expectedMessage = COMMON_RESPONSE_OK;
        final RestaurantResponse.Register expectedInformation = new RestaurantResponse.Register(1L);
        final RestaurantRequest.Register requestDto = RestaurantRequest.Register.builder()
            .name("???????????????")
            .categories(List.of("restaurant", "caffe"))
            .introduction("'???????????????'??? ?????????, ????????? ?????? ????????? ???????????? ?????? ?????????")
            .wayToGo("?????? ?????? ???????????? ?????? 9???")
            .zipcode("28921")
            .state("??????")
            .city("?????????")
            .simpleAddress("?????? ??????")
            .detailAddress("????????????????????? ????????? ????????? 11")
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
                        fieldWithPath("name").description("????????? ??????"),
                        fieldWithPath("categories").description("????????? ??????"),
                        fieldWithPath("introduction").description("?????? ??????"),
                        fieldWithPath("wayToGo").description("????????????"),
                        fieldWithPath("zipcode").description("????????????"),
                        fieldWithPath("state").description("????????????????????????(???/???)"),
                        fieldWithPath("city").description("????????????????????????(???/???/???)"),
                        fieldWithPath("simpleAddress").description("????????? ??????"),
                        fieldWithPath("detailAddress").description("?????? ??????"),
                        fieldWithPath("latitude").description("??????"),
                        fieldWithPath("longitude").description("??????"),
                        fieldWithPath("tipIds").description("???????????? ?????????"),
                        subsectionWithPath("openTimes").description("??????????????????"),
                        fieldWithPath("openTimes.[].day").description("???????????? ??????"),
                        fieldWithPath("openTimes.[].operationStart").description("?????? ????????????"),
                        fieldWithPath("openTimes.[].operationEnd").description("?????? ????????????"),
                        fieldWithPath("openTimes.[].breakStart").description("?????????????????? ????????????"),
                        fieldWithPath("openTimes.[].breakEnd").description("?????????????????? ????????????"),
                        fieldWithPath("images").description("????????? ?????? ??????")
                    ),
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        fieldWithPath("id").description("????????? ????????? ?????????")
                    )
                )
            );

        verify(restaurantService).register(requestDto);
    }

    @Test
    @DisplayName("?????? ?????? ?????? ?????? ?????????")
    void testFindRestaurants() {
        final Message expectedMessage = COMMON_RESPONSE_OK;
        final List<RestaurantResponse.Find> expectedResult = List.of(
            new RestaurantResponse.Find(
                15L,
                "???????????????",
                List.of("RESTAURANT", "CAFFE"),
                "?????? ??????",
                "/images/image_001",
                "'???????????????'??? ?????????, ????????? ?????? ????????? ???????????? ?????? ?????????"
            ),
            new RestaurantResponse.Find(
                14L,
                "??? ??? ??????",
                List.of("CAFFE"),
                "??????",
                "/images/image_017",
                "'????????????'?????? ?????????, '?????????'??? ???????????? ?????? ????????? ??? ?????? ??????"
            ),
            new RestaurantResponse.Find(
                13L,
                "???????????? ?????????",
                List.of("RESTAURANT"),
                "??????/??????",
                "/images/image_021",
                "????????? ?????????????????? ??????????????? ?????? ??? ?????? ???????????? ?????????"
            ),
            new RestaurantResponse.Find(
                12L,
                "?????? ??? ??????",
                List.of("RESTAURANT"),
                "?????????",
                "/images/image_031",
                "?????? ???????????? ??? ??? ????????????, '????????? ?????????' ?????? ?????? ??????"
            ),
            new RestaurantResponse.Find(
                11L,
                "???????????? ??????",
                List.of("RESTAURANT"),
                "?????? ??????",
                "/images/image_002",
                "????????? ????????? ????????? ?????? ????????? ?????? ?????? ?????? ?????????"
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
                        parameterWithName("page").description("????????? ??????").optional(),
                        parameterWithName("size").description("???????????? ??????").optional(),
                        parameterWithName("sort").description("?????? ????????? ?????? ?????? ??? ?????? ??????").optional()
                    ),
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        subsectionWithPath("links").description("????????? ??????"),
                        fieldWithPath("content.[].id").description("?????? ?????????"),
                        fieldWithPath("content.[].name").description("????????? ??????"),
                        fieldWithPath("content.[].categories").description("????????? ??????"),
                        fieldWithPath("content.[].address").description("????????? ????????? ??????"),
                        fieldWithPath("content.[].image").description("????????? ?????? ??????"),
                        fieldWithPath("content.[].introduction").description("?????? ?????????"),
                        fieldWithPath("content.[].links").description("???"),
                        subsectionWithPath("page").description("????????? ?????? ?????? ??????"),
                        fieldWithPath("page.size").description("???????????? ??????"),
                        fieldWithPath("page.totalElements").description("?????? ????????? ??????"),
                        fieldWithPath("page.totalPages").description("?????? ???????????? ??????"),
                        fieldWithPath("page.number").description("?????? ????????? ??????")
                    )
                )
            );

        verify(restaurantService).find(pageRequest);
    }

    @Test
    @DisplayName("?????? ?????? ?????? ??????")
    void testFindById() {
        final Message expectedMessage = COMMON_RESPONSE_OK;
        final RestaurantResponse.FindWithDetail expectedInformation =
            RestaurantResponse.FindWithDetail.builder()
                .id(1L)
                .name("???????????????")
                .images(List.of("/images/image_001", "/images/image_002"))
                .menus(List.of(
                    new RestaurantResponse.FindWithDetail.Menu(1L, "????????? ?????????", 9000, "/images/image_003"),
                    new RestaurantResponse.FindWithDetail.Menu(2L, "??????", 9000, "/images/image_004")
                ))
                .wayToGo("?????? ?????? ???????????? ?????? 9???")
                .simpleAddress("?????? ??????")
                .detailAddress("????????????????????? ????????? ????????? 111")
                .openTimes(List.of(
                    new RestaurantResponse.FindWithDetail.OpenTime("MON", "08:00-22:00", "15:00-17:00"),
                    new RestaurantResponse.FindWithDetail.OpenTime("TUE", "08:00-22:00", "15:00-17:00"),
                    new RestaurantResponse.FindWithDetail.OpenTime("WED", "08:00-22:00", "15:00-17:00"),
                    new RestaurantResponse.FindWithDetail.OpenTime("THU", "08:00-22:00", "15:00-17:00"),
                    new RestaurantResponse.FindWithDetail.OpenTime("SAT", "08:00-22:00", "15:00-17:00"),
                    new RestaurantResponse.FindWithDetail.OpenTime("SUN", "08:00-22:00", "15:00-17:00")
                ))
                .introduction("'???????????????'??? ?????????, ????????? ?????? ????????? ???????????? ?????? ?????????")
                .tips(List.of(
                    "?????? ?????? ????????? ?????? ??????",
                    "?????? ????????? ??????"
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
                        parameterWithName("id").description("?????? ?????????")
                    ),
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        fieldWithPath("id").description("?????? ?????????"),
                        fieldWithPath("name").description("????????? ??????"),
                        subsectionWithPath("menus").description("?????? ??????"),
                        fieldWithPath("menus.[].id").description("?????? ?????????"),
                        fieldWithPath("menus.[].name").description("?????? ??????"),
                        fieldWithPath("menus.[].price").description("?????? ??????"),
                        fieldWithPath("menus.[].image").description("?????? ????????? ??????"),
                        fieldWithPath("wayToGo").description("?????? ??????"),
                        fieldWithPath("simpleAddress").description("????????? ??????"),
                        fieldWithPath("detailAddress").description("?????? ??????"),
                        subsectionWithPath("openTimes").description("??????????????????"),
                        fieldWithPath("openTimes.[].day").description("??????"),
                        fieldWithPath("openTimes.[].servingTime").description("?????? ??????"),
                        fieldWithPath("openTimes.[].breakTime").description("???????????? ??????"),
                        fieldWithPath("introduction").description("?????? ?????????"),
                        fieldWithPath("tips").description("?????????"),
                        fieldWithPath("images").description("????????? ?????? ??????")
                    )
                )
            );

        verify(restaurantService).findById(1L);
    }

    @Test
    @DisplayName("???????????? ?????? ?????? ?????? ?????????")
    void testFindCategories() {
        final Message expectedMessage = COMMON_RESPONSE_OK;
        final List<RestaurantResponse.FindCategory> expectedInformation = List.of(
            new RestaurantResponse.FindCategory(
                "CAFFE"
            ),
            new RestaurantResponse.FindCategory(
                "RESTAURANT"
            )
        );

        when(restaurantService.findCategories()).thenReturn(expectedInformation);

        webTestClient
            .get()
            .uri("/api/restaurants/categories")
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody(new ParameterizedTypeReference<HttpResponseBody<List<RestaurantResponse.FindCategory>>>() {
            })
            .consumeWith(response -> {
                HttpResponseBody<List<RestaurantResponse.FindCategory>> responseBody = response.getResponseBody();
                assertThat(responseBody.getCode()).isEqualTo(expectedMessage.getCode());
                assertThat(responseBody.getMessage()).isEqualTo(expectedMessage.getMessage());
                assertThat(responseBody.getInformation()).isEqualTo(expectedInformation);
            })
            .consumeWith(
                document(
                    "categories/findAll",
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        fieldWithPath("name").description("??????????????? ??????")
                    )
                )
            );

        verify(restaurantService).findCategories();
    }

    @Test
    @DisplayName("?????? ?????? ?????? ?????????")
    void testRegisteringMenu() {
        final Message expectedMessage = COMMON_RESPONSE_OK;
        final RestaurantResponse.RegisterMenu expectedInformation = new RestaurantResponse.RegisterMenu(1L);
        final RestaurantRequest.RegisterMenu requestDto = RestaurantRequest.RegisterMenu.builder()
            .name("????????? ?????????")
            .image("/images/image_003")
            .price(9000)
            .build();

        when(restaurantService.registerMenu(1L, requestDto)).thenReturn(expectedInformation);

        webTestClient
            .post()
            .uri("/api/restaurants/{restaurantId}/menus", 1L)
            .accept(APPLICATION_JSON)
            .bodyValue(requestDto)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody(new ParameterizedTypeReference<HttpResponseBody<RestaurantResponse.RegisterMenu>>() {
            })
            .consumeWith(response -> {
                HttpResponseBody<RestaurantResponse.RegisterMenu> responseBody = response.getResponseBody();
                assertThat(responseBody.getCode()).isEqualTo(expectedMessage.getCode());
                assertThat(responseBody.getMessage()).isEqualTo(expectedMessage.getMessage());
                assertThat(responseBody.getInformation()).isEqualTo(expectedInformation);
            })
            .consumeWith(
                document(
                    "menus/register",
                    pathParameters(
                        parameterWithName("restaurantId").description("????????? ????????? ????????? ?????????")
                    ),
                    requestFields(
                        fieldWithPath("name").description("?????? ??????"),
                        fieldWithPath("price").description("?????? ??????"),
                        fieldWithPath("image").description("?????? ????????? ??????")
                    ),
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        fieldWithPath("id").description("????????? ?????? ?????????")
                    )
                )
            );
        verify(restaurantService).registerMenu(1L, requestDto);
    }

    @Test
    @DisplayName("????????? ?????? ?????? ?????? ?????????")
    void testFindTips() {
        final Message expectedMessage = COMMON_RESPONSE_OK;
        final List<RestaurantResponse.FindTip> expectedResult = List.of(
            new RestaurantResponse.FindTip(
                1L,
                "First Tip"
            ),
            new RestaurantResponse.FindTip(
                2L,
                "Second Tip"
            )
        );

        when(restaurantService.findTips()).thenReturn(expectedResult);

        webTestClient
            .get()
            .uri("/api/restaurants/tips")
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "application/json")
            .expectBody(new ParameterizedTypeReference<HttpResponseBody<List<RestaurantResponse.FindTip>>>() {
            })
            .consumeWith(response -> {
                HttpResponseBody<List<RestaurantResponse.FindTip>> responseBody = response.getResponseBody();
                assertThat(responseBody.getCode()).isEqualTo(expectedMessage.getCode());
                assertThat(responseBody.getMessage()).isEqualTo(expectedMessage.getMessage());
                assertThat(responseBody.getInformation()).isEqualTo(expectedResult);
            })
            .consumeWith(
                document(
                    "tips/findAll",
                    responseFields(
                        beneathPath("information").withSubsectionId("information"),
                        fieldWithPath("id").description("??? ?????????"),
                        fieldWithPath("content").description("??? ??????")
                    )
                )
            );

        verify(restaurantService).findTips();
    }

}
