package com;

//
//@con
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket api() {
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.furnituremanager.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo())
//                .useDefaultResponseMessages(false);
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo("POC Project Demo",
//                "This is the API",
//                "1.0",
//                "urn:tos",
//                ApiInfo.DEFAULT.getContact(),
//                "Apache 2.0",
//                "http");
//    }
//}