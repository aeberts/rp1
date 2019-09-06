(ns rp1.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [rp1.events :as events]
   [rp1.routes :as routes]
   [rp1.views :as views]
   [rp1.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
