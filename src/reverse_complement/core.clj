(ns reverse_complement.core
  {:doc "complementing a strand of dna"}
  (:use clj-getopts.core)
  (:require clojure.string)
  (:gen-class)
  )

(defn transcribe [data]
  (let [smap {\A \T
              \T \A
              \C \G
              \G \C}]
    (replace smap data)))

(defn rev-complement [data]
  (let [transcribed (transcribe data)]
    (reverse transcribed)))

(defn read-data [opts]
  (let [fn (get opts :infile)
        data (clojure.string/trim-newline (slurp fn))]
    data))

(defn write-data [opts data]
  (let [fn (get opts :outfile)
    out-data (apply str data)]
    (spit fn out-data)))

(defn -main [& args]
  (let [opts (getopts (options "io?" {:infile :arg :outfile :arg}) args)
        data (read-data opts)
        res (rev-complement data)]
    (write-data opts res)))

