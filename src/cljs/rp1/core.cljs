(ns rp1.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [re-posh.core :as re-posh]
   [rp1.events :as events]
   [rp1.routes :as routes]
   [rp1.views :as views]
   [rp1.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn mount-app-element []
  (reagent/render-component [views/main-panel]
                            (.getElementById js/document "app")))

(defn init []
  (re-posh/dispatch-sync [::events/initialize-db])
  (mount-app-element))

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element))

(defonce initialize-block
  (do
    (re-posh/dispatch-sync [::events/initialize-db])
    (mount-app-element)
    true))
