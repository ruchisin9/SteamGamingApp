package com.assignedpro.nj.steam.Response;

import java.util.List;

/**
 * Created by Ankur_ on 6/18/2018.
 */

public class GetAllDetails {
    String success;
  CompleteData gamedata;

    public static class CompleteData {
        String type;
        String name;
        String steam_appid;
        String required_age;
        String is_free;
        String detailed_description;
        String about_the_game;
        String short_description;
        String supported_languages;
        String header_image;
        String website;
        PcRequirement pc_requirements;
        MacRequirement mac_requirements;
        LinuxRequirement linux_requirements;
        String legal_notice;
        String ext_user_account_notice;
        List<Developer> developers;
        List<Publisher> publishers;
        PriceOverview price_overview;
        List<Packages> packages;
        List<PackageGroups> package_groups;
        Platforms platforms;
        List<Categories> categories;
        List<GenresClass> genres;
        List<Screenshots> screenshots;
        List<Movies> movies;
        Achievements achievements;
        ReleaseDate release_date;
        SupportInfo support_info;
        String background;

    }
    private class ReleaseDate {
        String coming_soon;
        String date;
    }

    private class SupportInfo {
        String url;
        String email;
    }

        private class Developer {
        }

        private class Publisher {
        }

        private class Dlc_Data {
        }

        private class PcRequirement {

            String minimum;
            String recommended;
        }

        private class MacRequirement {

            String minimum;
            String recommended;
        }

        private class LinuxRequirement {

            String minimum;
            String recommended;
        }

        private class PriceOverview {
            String currency;
            String initial;
            String finalS;
            String discount_percent;

        }

        private class Packages {
        }

        private class PackageGroups {
            String name;
            String title;
            String description;
            String selection_text;
            String save_text;
            String display_type;
            String is_recurring_subscription;
            List<Subs> subs;


            private class Subs {
                String packageid;
                String percent_savings_text;
                String percent_savings;
                String option_text;
                String option_description;
                String can_get_free_license;
                String is_free_license;
                String price_in_cents_with_discount;

            }
        }

        private class Platforms {

            String windows;
            String mac;
            String linux;
        }

        private class Categories {

            String id;
            String description;

        }

        private class GenresClass {

            String id;
            String description;

        }

        private class Screenshots {

            String id;
            String path_thumbnail;
            String path_full;
        }

        private class Achievements {
                 String total;
                 List<Highlighted> highlighted;

            private class Highlighted {
                String name;
                String path;

            }
        }

    public static class Movies {
        String id;
        String name;
        String thumbnail;
        WebMap webm;
        String highlight;

        private class WebMap {
            String datamap;
            String max;

        }
    }
    }
